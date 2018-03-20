/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import upec.groupe1.session.Email;

/**
 *
 * @author drajasin
 */
public class MailServlet extends HttpServlet {

    private final String MAIL = "/SendMail";
    private final String MAILRESPONSE = MAIL + "/response";
    @EJB
    Email email;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {

            Map<String, String[]> params = request.getParameterMap();
            String path = request.getServletPath();
            switch (path) {
                case MAIL: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }

                case MAILRESPONSE: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {

            String path = request.getServletPath();
            switch (path) {
                case MAIL: {
                    String emails = (String) request.getParameter("emailAddress");
                    String object = (String) request.getParameter("object");
                    String message = (String) request.getParameter("message");
                    System.err.println("PRINT mail" + emails + object + message);

                    try {
                        email.setBody(message);
                        email.setTo(emails);
                        email.setFrom(emails);
                        email.setSubject(object);
                        email.send();
                    } catch (MessagingException ex) {
                        Logger.getLogger(MailServlet.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("Impossible d'envoyer le mail" + emails + object + message);
                    }
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }

                case MAILRESPONSE: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
