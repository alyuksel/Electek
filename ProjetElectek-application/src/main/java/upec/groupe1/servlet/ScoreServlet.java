/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.dto.Candidate;
import upec.groupe1.dto.Score;
import upec.groupe1.entities.VoteOffices;
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
    private final String RESULTS_LEGISLATIVE = RESULTS + "/legislatives";

    @EJB
    ResultsEJB resultsEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (RESULTS_PRESIDENTIELLE.equals(path) || RESULTS_LEGISLATIVE.equals(path)) {
            Map<String, String[]> params = request.getParameterMap();
            if (params.size() > 0) {
                String turn ="";
                String year ="";
                for (String key : params.keySet()) {
                    try {
                        if (key.equalsIgnoreCase("turn") && params.get(key)[0] != null) {
                            turn = params.get(key)[0];
                            System.out.println(turn);
                        }
                        if (key.equalsIgnoreCase("year") && params.get(key)[0] != null) {
                            year = params.get(key)[0];
                            System.out.println(year);
                        }
                    } catch (Exception ex) {}
                };
                if (!turn.equals("") && !turn.equals("")) {
                    String election = RESULTS_PRESIDENTIELLE.equals(path) ? "Presidentielle" : "Legislatives";
                    List<Candidate> candidates = resultsEJB.getCandidatesFiltred(election, year, turn);
                    request.setAttribute("turn", turn);
                    request.setAttribute("year", year);
                    request.setAttribute("candidates", candidates);
                }
            }
            request.setAttribute("isScore", false);
            request.setAttribute("path", path);
            this.getServletContext().getRequestDispatcher("/WEB-INF/scores/CandidateScore.jsp").forward(request, response);
        }
        if (RESULTS.equals(path)) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/scores/Scores.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        String turn = request.getParameter("turn");
        String year = request.getParameter("year");
        String arr = request.getParameter("place");
        String fullName = request.getParameter("fullName");
        if (turn != null && year != null) {
            String election = "";
            switch (path) {
                case RESULTS_PRESIDENTIELLE: {
                    election = "Presidentielle";
                }
                break;
                case RESULTS_LEGISLATIVE: {
                    election = "Legislatives";
                }
                break;
            }
            System.out.println(turn + " " + election + " " + year + " " + arr + " " + fullName);
            List<Candidate> candidates = resultsEJB.getCandidatesFiltred(election, year, turn);
            if (arr != null && fullName != null) {

                //Cas pour recuperer les resultats d'un candidat
                String[] splitfullName = fullName.split("_");
                Score score;

                if ("global".equals(arr)) {
                    score = resultsEJB.getScoreByCandidate(splitfullName[0], splitfullName[1], turn, election, year);
                } else {
                    score = resultsEJB.getScoreByCandidateByArrondisse(splitfullName[0], splitfullName[1], turn, election, arr, year);
                }

                request.setAttribute("isScore", true);
                request.setAttribute("score", score);
            } else {
                //Cas pour recuperer les candidats
                request.setAttribute("isScore", false);
                request.setAttribute("place", turn);
            }
            request.setAttribute("candidates", candidates);

            request.setAttribute("year", year);
            request.setAttribute("turn", turn);
            request.setAttribute("path", path);
            this.getServletContext().getRequestDispatcher("/WEB-INF/scores/CandidateScore.jsp").forward(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
