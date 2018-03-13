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
import java.awt.Polygon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AMM
 */
public class TEST {
      
    private Client client;
    private ClientResponse clientResponse;
    private WebResource webResource;
    
    
    public static void main(String[] args){
        TEST t = new TEST();
        
        
        String json = t.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            
            for(Map<String,Object> m : records){
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                Double arr = (Double) ms.get("arrondisse");
                 
                Polygon p = new Polygon();
                
                Map<String,Object> ml = (Map<String,Object>) ms.get("geo_shape");
                try{
                List<List<List<Object>>> ll = (List<List<List<Object>>>) ml.get("coordinates");
                for (List<List<Object>> ll2: ll){
                    for (List<Object> ll3: ll2){
                        
                        Double x = (Double) ll3.get(0);
                        Double y = (Double) ll3.get(1);
                        p.addPoint(x.intValue(), y.intValue());
                   
                        
                }
                
                
                
            } } catch(NullPointerException|ClassCastException np){
                            System.err.println("null");
                    }
                System.err.println(p);
            }
            System.out.println(records); 
            
    }
    private   String getResults(String URL){
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


 