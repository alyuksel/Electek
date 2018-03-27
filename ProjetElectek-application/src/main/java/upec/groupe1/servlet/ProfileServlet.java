/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import upec.groupe1.tools.Tools;

/**
 *
 * @author adam
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {
    @EJB
    UserEJB usersEJB;
   private void processe(HttpServletRequest request,HttpSession session){
       String login = (String) session.getAttribute("user");
            if(login!=null){
                 Users user;
                try {
                    user = usersEJB.find(Users.class,login);
                    request.setAttribute("user", user);

                } catch (NotFoundException ex) {
                    Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
   }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }else{
            //pour afficher tout les users
            if(Tools.checkAccess(session)){
                List<Users> users = (List<Users>) usersEJB.getAllData("SELECT u FROM Users u ", 0);
                request.setAttribute("users", users);
                request.setAttribute("access", true);
            }
            //Pour afficher les info de l'user courant
            processe(request,session);
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }else{
            
            if(Tools.checkAccess(session)){
                String login =(String) request.getParameter("login");
                if(login !=null){
                    Users user;
                    try {
                        user = usersEJB.find(Users.class,login);
                        if(user.getType().equals("user")){
                            usersEJB.changeStatus(user.getId(), "admin");
                        }else{
                            usersEJB.changeStatus(user.getId(), "user");
                        }
                    } catch (NotFoundException ex) {
                        Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                List<Users> users = (List<Users>) usersEJB.getAllData("SELECT u FROM Users u ", 0);
                request.setAttribute("users", users);
                request.setAttribute("access", true);
                
            }
            
            String email =(String) request.getParameter("mail");
            if(email!=null && !email.isEmpty()){
                String login = (String) session.getAttribute("user");
                if(login!=null){
                     Users user;
                    try {
                        user = usersEJB.find(Users.class,login);
                        if(user!=null){
                            usersEJB.changeMail(user.getId(), email);
                             request.setAttribute("messageMail","Mise a jour de l'adresse mail ");
                        }
                    } catch (NotFoundException ex) {
                        Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            String password =(String) request.getParameter("password");
            if(password!=null && !password.isEmpty()){
                String login = (String) session.getAttribute("user");
                if(login!=null){
                     Users user;
                    try {
                        user = usersEJB.find(Users.class,login);
                        if(user!=null){
                            usersEJB.changePassword(user.getId(), password); 
                            request.setAttribute("messagePassword","Mise a jour du mot de passe");
                        }
                    } catch (NotFoundException ex) {
                        Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            processe(request,session);
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/profile.jsp").forward(request, response);
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
