/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import upec.groupe1.entities.Users;
import upec.groupe1.session.UserEJB;

/**
 *
 * @author adam
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {
    @EJB
    UserEJB usersEJB;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }else{
            
            List<Users> users = (List<Users>) usersEJB.getAllData("SELECT u FROM Users u ", 0);
            request.setAttribute("users", users);
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
