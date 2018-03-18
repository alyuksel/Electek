/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adam
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null){
            session.setAttribute("user", null);
            if (request.getCookies() != null)
                for (Cookie c :request.getCookies()) {
                    if(c.getValue().equals("user"))
                        c.setValue("");
                        c.setPath("/");
                        c.setMaxAge(0);
                        response.addCookie(c);
                }
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }


  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
