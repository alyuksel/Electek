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
                case RESULTS_PRESIDENTIELLE : {
                    String[] fullName = request.getParameter("fullName").split(" ");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    Score score = null;
                    if("global".equals(arrondissement))
                        score = resultsEJB.getScoreByCandidate(new String(fullName[0].getBytes("UTF-8"), "UTF8"), new String(fullName[1].getBytes("UTF-8"), "UTF8"), turn, "Présidentielle");
                    else
                        score = resultsEJB.getScoreByCandidateByArrondisse(new String(fullName[0].getBytes("UTF-8"), "UTF8"), new String(fullName[1].getBytes("UTF-8"), "UTF8"), turn, "Présidentielle", arrondissement);
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Présidentielle");
                    request.setAttribute("candidates", candidates);
                    request.setAttribute("isScore", true);
                    request.setAttribute("score", score);
                    request.setAttribute("path", path);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Results.jsp" ).forward( request, response );
                }break;
                case RESULTS_LEGISLATIVE : {
                    String[] fullName = request.getParameter("fullName").split(" ");
                    String turn = request.getParameter("turn");
                    String arrondissement = request.getParameter("place");
                    Score score = resultsEJB.getScoreByCandidate(new String(fullName[0].getBytes("UTF-8"), "UTF8"), new String(fullName[1].getBytes("UTF-8"), "UTF8"), turn, "Législatives");
                    List<Candidate> candidates = resultsEJB.getCandidatesByCaption("Législatives");
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
