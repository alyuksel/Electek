/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.affine.AffineBV;
import upec.groupe1.session.ResultsEJB;

/**
 *
 * @author adam
 */
@WebServlet("/resultatBV")
public class ResultsByBVServlet extends HttpServlet {
    @EJB
    private ResultsEJB results;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String election = request.getParameter("election");
        String turn = request.getParameter("turn");
        String year = request.getParameter("year");
        Map<String,AffineBV> map = results.getRankCandidateByBV(election, turn, year);
        System.out.println(map);
        request.setAttribute("results", map);
        this.getServletContext().getRequestDispatcher("/WEB-INF/bvResults/resultsByBV.jsp").forward(request, response);
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
