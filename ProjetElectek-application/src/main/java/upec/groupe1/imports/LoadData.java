/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.imports;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.Results;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.AdresseEJB;
import upec.groupe1.session.AttachedZoneEJB;
import upec.groupe1.session.ResultsEJB;
import upec.groupe1.session.VoteOfficeEJB;
import upec.groupe1.tools.Tools;

/**
 *
 * @author drajasin
 */
@WebServlet(name = "LoadData", urlPatterns = {"/LoadData"})
public class LoadData extends HttpServlet {

    @EJB
    private VoteOfficeEJB officeEJB;
    @EJB
    private AttachedZoneEJB attachezEJB;
    @EJB
    private AdresseEJB adresseEJB;
    @EJB
    private ResultsEJB resultsEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        boolean access = Tools.checkAccess(session);
        if (access) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/load/LoadDatas.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        boolean access = Tools.checkAccess(session);
        if (!access) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {

            String amount = request.getParameter("amount");
            int amountInt;
            try {
                amountInt = Integer.parseInt(amount);
                if (amountInt < -1 || amountInt == 0) {
                    throw new NumberFormatException("Invalid Number");
                }
            } catch (NumberFormatException e) {
                amountInt = 1000;
            }
            try {

                resultsEJB.delete(Results.class);
                adresseEJB.delete(Adresse.class);
                attachezEJB.delete(AttachedZone.class);
                officeEJB.delete(VoteOffices.class);

                officeEJB.importFromAPI();
                attachezEJB.importFromAPI();
                adresseEJB.importFromAPI(amountInt);
                resultsEJB.create();

                Long numberOffices = officeEJB.count(VoteOffices.class);
                Long numberZones = attachezEJB.count(AttachedZone.class);
                Long numberAdresses = adresseEJB.count(Adresse.class);
                Long numberResults = resultsEJB.count(Results.class);

                request.setAttribute("countVoteOffices", numberOffices);
                request.setAttribute("countAttachedZones", numberZones);
                request.setAttribute("countAdresses", numberAdresses);
                request.setAttribute("countResults", numberResults);
                this.getServletContext().getRequestDispatcher("/WEB-INF/load/LoadDatas.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                this.getServletContext().getRequestDispatcher("/WEB-INF/load/LoadDatas.jsp").forward(request, response);
            }
        }
    }
}
