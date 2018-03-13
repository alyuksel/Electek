/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
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
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.session.AttachedZoneEJB;


/**
 *
 * @author drajasin
 */
@WebServlet(name = "test", urlPatterns = {"/LoadAttachedZone"})
public class LoadAttachedZone extends HttpServlet {
    @EJB
    private AttachedZoneEJB attachezEJB;
    
    private Client client;
    private ClientResponse clientResponse;
    private WebResource webResource;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet remoteDatasServletAttachedZone</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String json = getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
        
            for (Map<String,Object> o : records){
                Map<String,Object> infos = (Map<String,Object>) o.get("fields");
                AttachedZone aZ = new AttachedZone();
                
                
                //TODO : DRM - VERSION PAS PAS PAS PAS DU TOUT PROPRE A FINIR AU PROPRE MERCI
                
                
                String arr = infos.get("arrondisse").toString();
                System.out.println(infos.get("geo_point_2d"));
                String numBV = infos.get("num_bv").toString();
                Double arrDou = Double.parseDouble(arr);
                Double numBVDOu = Double.parseDouble(numBV); 
                aZ.setNumber(numBVDOu.intValue());
                aZ.setArr(arrDou.intValue());
                attachezEJB.create(aZ);
                
            }
            
            out.println("<h1>Servlet remoteDatasServletAttachedZone at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
    private  String getResults(String URL){
        client = Client.create();
        webResource = client.resource(URL);
        clientResponse = webResource.accept("application/json")
                   .get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ clientResponse.getStatus());
		}
        
        
        return  clientResponse.getEntity(String.class);
    }
}
