/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.tools;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import upec.groupe1.entities.Results;

/**
 *
 * @author adam
 */
public class Tools {
     public static String getResults(String URL){
        Client client;
        ClientResponse clientResponse;
        WebResource webResource;
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
     
     public static Map<String,Long> getMapCalculated(List<Results> l,VotesStats vs){
          Map<String,Long> score = new HashMap<>();
          
        for (Results r : l){
            String key = r.getCandidateFN()+"_"+r.getCandidateLN();
            if (score.containsKey(key)){
                Long s = new Long(0);
                if (vs.equals(vs.NOMBREVOIE)){
                    s = r.getNbVoie() + score.get(key);
                    score.put(key, s);
                }else{
                    s = r.getNbExprime() + score.get(key);
                    score.put(key,s);
                }
            }else{
                Long s = new Long(0);
                if (vs.equals(vs.NOMBREVOIE)){
                    s = r.getNbVoie();
                    score.put(key, s);
                }else{
                    s = r.getNbExprime();
                    score.put(key,s);
                }
            }
        }
        return score;
     }
     
     public static Map<String,Long> getScore(Map<String,Long> scores,Map<String,Long> votes){
          Map<String,Long> purcents = new HashMap<>();
           for (String key : scores.keySet()){
                    purcents.put(key,(scores.get(key)*100)/votes.get(key));
                }
           return purcents;
        }
     }
     

