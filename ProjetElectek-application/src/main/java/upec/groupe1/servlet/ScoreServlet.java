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
            String path = request.getServletPath();
            if(RESULTS_PRESIDENTIELLE.equals(path) || RESULTS_LEGISLATIVE.equals(path)){
              request.setAttribute("isScore", false);
              request.setAttribute("path", path);
              this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String path = request.getServletPath();

        String turn = request.getParameter("turn");
        String arrondissement = request.getParameter("place");
        String year = request.getParameter("year");    
        if(turn !=null && arrondissement!=null && year!=null)
        {
            List<Candidate> candidates =null;
            String election = "";
            switch(path){
                case RESULTS_PRESIDENTIELLE : {
                    election = "Presidentielle";
                }break;
                case RESULTS_LEGISLATIVE : {
                    election = "Legislatives";            
                }break;
            }
            
            if("global".equals(arrondissement))
                candidates = resultsEJB.getCandidatesFiltred(election,year,turn);
            else
                candidates = resultsEJB.getCandidatesFiltred(election,year,turn, Double.valueOf(arrondissement));
            
            request.setAttribute("candidates", candidates);
            request.setAttribute("isScore", false);
            request.setAttribute("year", path);
            request.setAttribute("turn", path);
            request.setAttribute("place", path);
            request.setAttribute("path", path);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/CandidateScore.jsp" ).forward( request, response );
                    
        }    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
