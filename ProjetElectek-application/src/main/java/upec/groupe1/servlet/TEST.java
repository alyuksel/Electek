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
            System.out.println("Begin");
            String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste");
            System.out.println("API OK");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
            int i = 0;
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            
            for(Map<String,Object> m : records){
                i++;
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                
                String prenom = (String) ms.get("prenom_du_candidat_ou_liste");
                String nom = (String) ms.get("nom_du_candidat_ou_liste");
                Double num_bv = (Double) ms.get("numero_de_bureau_de_vote_000_a_999");
                String libelle = (String) ms.get("libelle_du_scrutin");
                Double nb_exprime = (Double) ms.get("nombre_d_exprimes_du_bureau_de_vote");
                Double nb_votant = (Double) ms.get("nombre_de_votants_du_bureau_de_vote");
                Double nb_voie = (Double) ms.get("nombre_de_voix_du_candidat_ou_liste_obtenues_pour_le_bureau_de_vote");
                if(libelle.contains("Présidentielle") || libelle.contains("Législatives")){
                    String lib = libelle.split(" ")[0];
                    String année = libelle.split(" ")[1];
                    String tour = libelle.split("-")[1].split(" ")[1];
                    
                    System.out.println(prenom);
                    System.out.println(nom);
                    System.out.println(num_bv);
                    System.out.println(lib);
                    System.out.println(année);
                    System.out.println(tour);
                
                }
                
                
            }
       
      /* ArrayList<ArrayList<ArrayList<Double>>> l = new ArrayList<>();
       ArrayList<Double> dl = new ArrayList<>();
       dl.add(2.9);
       dl.add(3.3);
       ArrayList<ArrayList<Double>> dll = new ArrayList<>();
       dll.add(dl);
       l.add(dll);
       Double[][][] tab = new Double[l.size()][dll.size()][dl.size()];
       for (int i=0;i<l.size();i++){
           for(int j=0;j<dll.size();j++){
               for(int k=0;k<dl.size();k++){
                   tab[i][j][k]=dl.get(k);
               }
           }
       }
       for (Double[][] ll : tab){
            for (Double[] lll : ll){
                System.out.println(lll[0]);;
                System.out.println(lll[1]);
                
            }
        }
        */    
            
    }
   
}


 