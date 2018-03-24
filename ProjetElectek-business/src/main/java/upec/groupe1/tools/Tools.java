/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.tools;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adam
 */
public class Tools {

    public static String getResults(String URL) {
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

        return clientResponse.getEntity(String.class);
    }

    /**
     *
     * @param session
     * @return
     */
    public static boolean checkAccess(HttpSession session){
        boolean access = false;
        if (session.getAttribute("user") == null) {
            return access;
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update("admin".getBytes("UTF-8") );
                String type = new String(md.digest());
                if (session.getAttribute("right") != null) {
                    if (session.getAttribute("right").equals(type)) {
                        access = true;
                    }
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return access;
    }
}
