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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.dto.Candidate;
import upec.groupe1.dto.Score;
import upec.groupe1.session.ResultsEJB;

/**
 *
 * @author alpi
 */
public class ResultServlet extends HttpServlet {
    private final String RESULTS = "/results";
    private final String RESULTS_PRESIDENTIELLE = RESULTS + "/presidentielle";
    private final String RESULTS_LEGISLATIVE =RESULTS + "/legislatives";
    private final String RESULTS_PRESIDENTIELLE_CANDIDAT = RESULTS_PRESIDENTIELLE + "/candidat";
    private final String RESULTS_LEGISLATIVE_CANDIDAT = RESULTS_LEGISLATIVE + "/candidat";
    
    @EJB
    ResultsEJB resultsEJB;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            switch(path){
                case RESULTS_PRESIDENTIELLE : {
                    request.setAttribute("isScore", false);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Présidentielle");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("path", path);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Results.jsp" ).forward( request, response );
                }break;
                case RESULTS_LEGISLATIVE : {
                    request.setAttribute("isScore", false);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Législatives");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("path", path);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Results.jsp" ).forward( request, response );
                }break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            System.out.println(path);
        switch(path){
                case RESULTS_PRESIDENTIELLE_CANDIDAT : {
                    String lastName = request.getParameter("lastName");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    Score score = null;
                    score = resultsEJB.getScoreByCandidate(lastName, "François", turn, "Présidentielle");
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Présidentielle");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Results.jsp" ).forward( request, response );
                }break;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
