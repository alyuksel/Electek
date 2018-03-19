/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.session.Email;

/**
 *
 * @author drajasin
 */
public class MailServlet extends HttpServlet {
    private final String MAIL = "/SendMail";
    private final String MAILRESPONSE = MAIL+"/response";
    @EJB
    private Email email;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        String path = request.getServletPath();
        switch(path){
            case MAIL : {
                this.getServletContext().getRequestDispatcher( "/WEB-INF/mail/MailForm.jsp" ).forward( request, response );
                break;
            }

            case MAILRESPONSE : {
                this.getServletContext().getRequestDispatcher( "/WEB-INF/mail/MailForm.jsp" ).forward( request, response );
                break;
            }
            default :  
                   response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch(path){
            case MAIL : {
                 String emails =   (String)    request.getParameter("emailAddress");
                String object  =   (String)    request.getParameter("object");
                String message =   (String)    request.getParameter("message");
                System.err.println("PRINT mail"+emails+object+message);

            try {
                email.send(emails, object, message);
            } catch (MessagingException ex) {
                Logger.getLogger(MailServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Impossible d'envoyer le mail"+emails+object+message);
            }
                this.getServletContext().getRequestDispatcher( "/WEB-INF/mail/MailForm.jsp" ).forward( request, response );
                break;
            }

            case MAILRESPONSE : {
                this.getServletContext().getRequestDispatcher( "/WEB-INF/mail/MailForm.jsp" ).forward( request, response );
                break;
            }
            default :  
                   response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
