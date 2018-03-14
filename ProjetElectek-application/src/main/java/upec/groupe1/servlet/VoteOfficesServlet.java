/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.VoteOfficeEJB;

/**
 *
 * @author alpi
 */
@WebServlet(name = "VoteOfficesServlet", urlPatterns = {"/voteoffices"})
public class VoteOfficesServlet extends HttpServlet {
    @EJB
    private VoteOfficeEJB officesEJB;
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            Map<String, List<VoteOffices>> voteOfficesMap= officesEJB.getVoteOfficesByArrondissement();
            for(String key : voteOfficesMap.keySet()){
                out.println("Arrondissement : "+ key);
                out.println("<br>");
                for(VoteOffices vo: voteOfficesMap.get(key)){
                    out.println("numéro bureau de vote : " + vo.extractBvNum()+"     Adresse : "+ vo.getAdress());
                    out.println("<br>");
                }
                out.println("<br>");
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
