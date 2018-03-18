/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
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
@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {

    @EJB
    private UserEJB userEJB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/myResults.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/signIn.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name;
        String lastname;
        String login;
        String password;
        String confirmation;
        String mail;
        if (session.getAttribute("user") != null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/myResults.jsp").forward(request, response);
        } else {
                login = request.getParameter("username");
            try {
                Users user = userEJB.find(Users.class, login);
                redirectWithError("login existant, veuillez en saisir un nouveau", request, response);
            } catch (NotFoundException ex) {
                password = request.getParameter("password");
                if (verifylenPassword(password)){
                     confirmation = request.getParameter("password2");
                     if (verifyPasswords(password, confirmation)){
                        mail = request.getParameter("mail");
                        if (verifyMail(mail)){
                            name = request.getParameter("name");
                            lastname = request.getParameter("lastname"); 
                            if(verifyLenNames(name, lastname)){
                                Users user = new Users();
                                user.setId(login);
                                user.setPassword(password);
                                user.setName(name);
                                user.setLastName(lastname);
                                user.setMail(mail);
                                user.setType("user");
                                userEJB.create(user);
                                request.setAttribute("signin", "Vous avez été correctement enregistré");
                                request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                            }else{
                                redirectWithError("les noms et prenoms vides ne sont pas accéptés", request, response);
                            }
                        }else{
                            redirectWithError("mail incorrects veuillez en saisir un nouveau", request, response);
                        }
                     }else{
                         redirectWithError("les mots de passes saisis ne correspondent pas", request, response);
                     }
                     
                }else{
                     redirectWithError("Veuillez saisir un mot de passe de plus de 6 caractères", request, response);
                }
               
            }
            
            
            
            
        }

    }

    private boolean verifylenPassword(String password) {
        return (password != null && password.trim().length() >=6);
    }

    private boolean verifyPasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }
    private boolean verifyLenNames(String name,String lastname){
        return (name!=null 
                && lastname != null  
                && name.trim().length()>0 
                && lastname.trim().length()>0
                );
    }
    private boolean verifyMail(String email){
        return (email !=null 
                && email.trim().length()!=0 
                && email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )
                );    
    }
    
    private void redirectWithError(String error,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("error", error);
                this.getServletContext().getRequestDispatcher("/WEB-INF/signIn.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
