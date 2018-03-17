/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.entities.Results;
import upec.groupe1.session.ResultsEJB;

/**
 *
 * @author adam
 */
@WebServlet("/SortResults")
public class SortResultsServlet extends HttpServlet {
    @EJB
    private ResultsEJB results;
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String election = request.getParameter("election");
        String year = request.getParameter("year");
        String sort = request.getParameter("sort");
        System.out.println(election);
        System.out.println(year);
        System.out.println(sort);
        List<Results> resultList = results.getResultsByOrder(election, year, sort);
        request.setAttribute("results", resultList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
