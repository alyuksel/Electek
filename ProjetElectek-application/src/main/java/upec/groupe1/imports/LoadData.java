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
import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.Results;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.AdresseEJB;
import upec.groupe1.session.AttachedZoneEJB;
import upec.groupe1.session.ResultsEJB;
import upec.groupe1.session.VoteOfficeEJB;

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
    
   
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.getServletContext().getRequestDispatcher( "/WEB-INF/load/LoadDatas.jsp" ).forward( request, response );
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO : NOTION DE SESSION ET DE USER A RAJOUTER
        
        String amount = request.getParameter("amount");
        int amountInt;
        try{
            amountInt = Integer.parseInt(amount);
            if(amountInt<-1 || amountInt ==0){
                throw new NumberFormatException("Invalid Number");
            }
        }catch(NumberFormatException e){
            amountInt=1000;
        }
        try{
            
            resultsEJB.delete(Results.class);
            adresseEJB.delete(Adresse.class);
            attachezEJB.delete(AttachedZone.class);
            officeEJB.delete(VoteOffices.class);
            
            officeEJB.importFromAPI();
            attachezEJB.importFromAPI();
            adresseEJB.importFromAPI(amountInt);
            resultsEJB.importFromAPI();
            
            Long numberOffices = officeEJB.count(VoteOffices.class);
            Long numberZones = attachezEJB.count(AttachedZone.class);
            Long numberAdresses = adresseEJB.count(Adresse.class);
            Long numberResults = resultsEJB.count(Results.class);
            
            request.setAttribute("countVoteOffices", numberOffices);
            request.setAttribute("countAttachedZones", numberZones);
            request.setAttribute("countAdresses", numberAdresses);
            request.setAttribute("countResults", numberResults);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/load/LoadDatas.jsp" ).forward( request, response );
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            this.getServletContext().getRequestDispatcher( "/WEB-INF/load/LoadDatas.jsp" ).forward( request, response );
        }
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
