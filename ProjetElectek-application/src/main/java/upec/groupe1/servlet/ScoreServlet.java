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
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "ScoreServlet", 
        urlPatterns = {"/scores", "/scores/presidentielle", "/scores/legislatives"})
public class ScoreServlet extends HttpServlet {
    private final String RESULTS = "/scores";
    private final String RESULTS_PRESIDENTIELLE = RESULTS + "/presidentielle";
    private final String RESULTS_LEGISLATIVE =RESULTS + "/legislatives";

    @EJB
    ResultsEJB resultsEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            if(RESULTS_PRESIDENTIELLE.equals(path) || RESULTS_LEGISLATIVE.equals(path)){
               List<Candidate> candidates = null;
              switch(path){
                  case RESULTS_PRESIDENTIELLE : {
                      candidates = resultsEJB.getCandidatesByCaption("Presidentielle");
                  }break;
                  case RESULTS_LEGISLATIVE : {
                      candidates = resultsEJB.getCandidatesByCaption("Legislatives");
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
                        score = resultsEJB.getScoreByCandidate(fullName[0], fullName[1], turn, "Presidentielle", year);
                    else
                        score = resultsEJB.getScoreByCandidateByArrondisse(fullName[0], fullName[1], turn, "Presidentielle", arrondissement, year);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Presidentielle");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
                }break;
                case RESULTS_LEGISLATIVE : {
                    String[] fullName = request.getParameter("fullName").split("_");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    String year = request.getParameter("year");
                    Score score = null;
                    if("global".equals(arrondissement))
                        score = resultsEJB.getScoreByCandidate(fullName[0], fullName[1], turn, "Legislatives", year);
                    else
                        score = resultsEJB.getScoreByCandidateByArrondisse(fullName[0], fullName[1], turn, "Legislatives", arrondissement, year);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Legislatives");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
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
