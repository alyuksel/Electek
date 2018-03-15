/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.imports;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.VoteOfficeEJB;




/**
 *
 * @author adam
 */
@WebServlet("/remoteDatas")
public class remoteDatasServlet extends HttpServlet {
    
    @EJB
    private VoteOfficeEJB officeEJB;
    
    private Client client;
    private ClientResponse clientResponse;
    private WebResource webResource;

   

    
   @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            
            
            
            String json = getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=bureaux-de-votes&rows=-1&facet=lib&facet=cp");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
        
            for (Map<String,Object> o : records){
                Map<String,Object> infos = (Map<String,Object>) o.get("fields");
                VoteOffices vo = new VoteOffices();
                vo.setCaption((String)infos.get("lib"));
                vo.setAdress((String) infos.get("adresse"));
                vo.setNumber((String)infos.get("id_bv"));
                vo.setPostalCode((String)infos.get("cp"));
                
                officeEJB.create(vo);
                
            }
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("izizi");
            response.getWriter().flush();
            response.getWriter().close();
    }
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
    public String getServletInfo() {
        return "Short description";
    }
}
