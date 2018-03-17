/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.session.ResultsEJB;

/**
 *
 * @author adam
 */
@WebServlet("/Candidates")
public class CandidatesServlet extends HttpServlet {
    @EJB
    private ResultsEJB results;

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String,Long> res = results.getCandidates();
        request.setAttribute("res", res);
        this.getServletContext().getRequestDispatcher("/WEB-INF/candidates.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
