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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.VoteOfficeEJB;

/**
 *
 * @author alpi
 */
public class VoteOfficesServlet extends HttpServlet {
    private final String VOTE_OFFICE = "/voteoffices";
    private final String VOTE_OFFICE_BY_ARR = VOTE_OFFICE+"/arr";
    
    @EJB
    private VoteOfficeEJB officesEJB;
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            Map<String, String[]> params = request.getParameterMap();
            String path = request.getServletPath();
            switch(path){
                case VOTE_OFFICE : {
                    List<VoteOffices> liste = officesEJB.findNamedQuery("VoteOffices.findAll", VoteOffices.class);
                    request.setAttribute("ListeVoteOffices", liste);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/voteOffices/AllData_VoteOffices.jsp" ).forward( request, response );
                    break;
                }
                
                case VOTE_OFFICE_BY_ARR : {
                    
                    System.out.println("NI2323232");
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
                    break;
                }
                default :  
                       response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
