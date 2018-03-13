/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.tools;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author AMM
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
}
