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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import upec.groupe1.tools.Tools;

/**
 *
 * @author AMM
 */
public class TEST {
      
    private Client client;
    private ClientResponse clientResponse;
    private WebResource webResource;
    
    
    public static void main(String[] args){        
        
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=adresse_paris&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
            int i = 0;
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            
            for(Map<String,Object> m : records){
                i++;
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                
                String address = (String)ms.get("l_adr");
                
                Double num_voie = (Double) ms.get("n_voie");
                Double num_b = (Double) ms.get("n_sq_vo");
                Map<String,Object> geo = (Map<String,Object>) ms.get("geom");
                
                Double[] l =  (Double[]) geo.get("coordinates");
                Point2D p = new Point2D.Double(l[0],l[1]);
                System.out.println(p);         
            }
                
            
            
            
    }
   
}


 