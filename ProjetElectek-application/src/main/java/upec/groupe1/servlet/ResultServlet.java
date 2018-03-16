/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.session.ResultsEJB;

/**
 *
 * @author alpi
 */
public class ResultServlet extends HttpServlet {
    private final String RESULTS = "/results";
    private final String RESULTS_CANDIDAT = RESULTS + "/candidate";
    
    @EJB
    ResultsEJB resultsEJB;
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            String contexString = request.getContextPath();
            Map<String, String[]> params = request.getParameterMap();
            out.printf(contexString);
            switch(path){
                case RESULTS : {
                    List<String> attributes = new ArrayList<>();
                    attributes.add("izo");
                    attributes.add("izi");
                    String message = "Transmission de variables : OK ! ";
                    request.setAttribute( "test", attributes );
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Results.jsp" ).forward( request, response );
                }break;
                case RESULTS_CANDIDAT : {
                    resultsEJB.getCandidateResult("LE PEN", "1er");
                }break;
            }
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestGet(request, response);
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
