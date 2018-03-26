/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import upec.groupe1.entities.Users;
import upec.groupe1.session.Exceptions.NotFoundException;
import upec.groupe1.session.UserEJB;

/**
 *
 * @author adam
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserEJB userEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null){
            request.setAttribute("message", "Vous êtes connecté");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null) {
            request.setAttribute("message", "Vous êtes connecté");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            String login = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                Users user = userEJB.find(Users.class,login);
                if(user.isGoodPassword(password)){
                    session.setAttribute("user", login);
                    session.setMaxInactiveInterval(15*60);
                    String type = user.getType();
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        md.update(type.getBytes("UTF-8"));
                        type = new String(md.digest());
                        System.out.println(type);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("right", type);
                    request.setAttribute("message", "Vous êtes connecté");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }else{
                    request.setAttribute("error", "login ou mot de passe incorrects");
                    this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
                }
            } catch (NotFoundException ex) {
                request.setAttribute("error", "le login est inconnu");
                this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
            }
            

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
