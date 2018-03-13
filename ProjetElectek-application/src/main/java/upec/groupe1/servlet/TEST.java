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
import java.awt.Point;
import java.awt.Polygon;
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
        
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            try{
            for(Map<String,Object> m : records){
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                Double arr = (Double) ms.get("arrondisse");
                Double num_bv = (Double) ms.get("num_bv");
                List<Double> lp =  (List<Double>) ms.get("geo_point_2d");
                System.err.println(lp);
                Point point = new Point(lp.get(0).intValue(), lp.get(1).intValue());
                
                Polygon p = new Polygon();
                
                Map<String,Object> ml = (Map<String,Object>) ms.get("geo_shape");
                
                List<List<List<Object>>> ll = (List<List<List<Object>>>) ml.get("coordinates");
                for (List<List<Object>> ll2: ll){
                    for (List<Object> ll3: ll2){
                        
                        Double x = (Double) ll3.get(0);
                        Double y = (Double) ll3.get(1);
                        p.addPoint(x.intValue(), y.intValue());
                         
                    }
                } 
                System.err.println(p);
            }
            }catch(NullPointerException|ClassCastException np){
                            System.err.println("null");
                }
            System.out.println(records); 
            
    }
   
}


 