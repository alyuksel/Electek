/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import upec.groupe1.entities.Results;
import upec.groupe1.tools.Tools;

/**
 *
 * @author AMM
 */
@Stateless
public class ResultsEJB extends ConcretEJB<Results>{
    
    public void create(){
        System.out.println("Begin");
            String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste");
            System.out.println("API OK");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            
            for(Map<String,Object> m : records){
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                
                String prenom = (String) ms.get("prenom_du_candidat_ou_liste");
                String nom = (String) ms.get("nom_du_candidat_ou_liste");
                Double num_bv = (Double) ms.get("numero_de_bureau_de_vote_000_a_999");
                String libelle = (String) ms.get("libelle_du_scrutin");
                Double nb_exprime = (Double) ms.get("nombre_d_exprimes_du_bureau_de_vote");
                Double nb_votant = (Double) ms.get("nombre_de_votants_du_bureau_de_vote");
                Double nb_voie = (Double) ms.get("nombre_de_voix_du_candidat_ou_liste_obtenues_pour_le_bureau_de_vote");
                if(libelle.contains("Présidentielle") || libelle.contains("Législatives")){
                    Results res = new Results();
                    String lib = libelle.split(" ")[0];
                    String annee = libelle.split(" ")[1];
                    String tour = libelle.split("-")[1].split(" ")[1];
                    res.setCandidateFN(nom);
                    res.setCandidateLN(prenom);
                    
                    res.setNbExprime(nb_exprime.longValue());
                    res.setNbVotants(nb_votant.longValue());
                    res.setNbVoie(nb_voie.longValue());
                    res.setCaption(lib);
                    res.setYearEl(annee);
                    res.setTurn(tour);
                    
                    super.create(res);
                    
                
                }
        }
    }
}
