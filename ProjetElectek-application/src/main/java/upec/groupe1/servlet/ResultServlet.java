/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    ResultsEJB resultsEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            if(RESULTS_PRESIDENTIELLE.equals(path) || RESULTS_LEGISLATIVE.equals(path)){
               List<Candidate> candidates = null;
              switch(path){
                  case RESULTS_PRESIDENTIELLE : {
                      candidates = resultsEJB.getCandidatesByCaption("Présidentielle");
                  }break;
                  case RESULTS_LEGISLATIVE : {
                      candidates = resultsEJB.getCandidatesByCaption("Législatives");
                  }break;
              }
              request.setAttribute("isScore", false);
              request.setAttribute("candidates", candidates);
              request.setAttribute("path", path);
              this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
        switch(path){
                case RESULTS_PRESIDENTIELLE : {
                    String[] fullName = request.getParameter("fullName").split("_");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    String year = request.getParameter("year");
                    Score score = null;
                    if("global".equals(arrondissement))
                        score = resultsEJB.getScoreByCandidate(fullName[0], fullName[1], turn, "Présidentielle", year);
                    else
                        score = resultsEJB.getScoreByCandidateByArrondisse(fullName[0], fullName[1], turn, "Présidentielle", arrondissement, year);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Présidentielle");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
                    request.setAttribute("candidatFullName", request.getParameter("fullName"));
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
                }break;
                case RESULTS_LEGISLATIVE : {
                    String[] fullName = request.getParameter("fullName").split("_");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    String year = request.getParameter("year");
                    Score score = null;
                    if("global".equals(arrondissement))
                        score = resultsEJB.getScoreByCandidate(fullName[0], fullName[1], turn, "Législatives", year);
                    else
                        score = resultsEJB.getScoreByCandidateByArrondisse(fullName[0], fullName[1], turn, "Législatives", arrondissement, year);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Législatives");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
                    request.setAttribute("candidatFullName", request.getParameter("fullName"));
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
                }break;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
