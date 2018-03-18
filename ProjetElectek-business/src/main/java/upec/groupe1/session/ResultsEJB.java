/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import upec.groupe1.affine.AffineBV;
import upec.groupe1.entities.Results;
import upec.groupe1.tools.Tools;

/**
 *
 * @author adam
 */
@Stateless
public class ResultsEJB extends ConcretEJB<Results>{
    private static Map<Long,AffineBV> mapOfBV;
    
    
    
    public Map<Long,AffineBV> getRankCandidateByBV(String caption, String turn,String year){
        mapOfBV = new HashMap<>();
        System.out.println(caption);
        System.out.println(turn);
        System.out.println(year);
        List<Results> list = em.createNamedQuery("Results.getResultsByBV",Results.class).setParameter("caption", caption)
                .setParameter("turn",turn).setParameter("year", year).getResultList();
        for (Results r : list){
            System.out.println(r.getCandidateFN());
            Long key = r.getNumBV();
            if (mapOfBV.containsKey(key)){
                mapOfBV.get(key).addCandidate(r);
            }else{
                AffineBV aff = new AffineBV(r);
                mapOfBV.put(key, aff);
            }
        }
        return mapOfBV;
    }
    public Map<Long,AffineBV> getMapAffined(){
        return mapOfBV;
    }
    public List<Results> getResults(){
        return  em.createNamedQuery("Results.getResults",Results.class)
                .getResultList();
    };
    
     public List<Results> getResultsByOrder(String election, String year,Object order){
        return  em.createQuery("SELECT r FROM Results r WHERE r.caption = :caption and r.yearEl = :year ORDER BY "+order,Results.class)
                .setParameter("caption", election)
                .setParameter("year", year)
                .getResultList();
    };
    
    
    
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Results> rt = cq.from(Results.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
     public List<Results> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Results.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public void getFromAPI(String apiAddr){
        System.out.println("Begin");
            String json = Tools.getResults(apiAddr);
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
                if(libelle.contains("Présidentielle") || libelle.contains("Législatives") ){
                    Results res = new Results();
                    String lib = libelle.split(" ")[0].replaceAll("é", "e");
                    String annee = libelle.split(" ")[1];
                    String tour = libelle.split("-")[1].split(" ")[1].replaceAll("er", "").replaceAll("eme", "").replaceAll("ème", "");
                    res.setCandidateFN(nom);
                    res.setCandidateLN(prenom);
                    res.setNumBV(num_bv.longValue());
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
    public void create(){
        getFromAPI("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste&refine.libelle_du_scrutin=Pr%C3%A9sidentielle+2017+-+1er+tour");
        getFromAPI("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste&refine.libelle_du_scrutin=L%C3%A9gislatives+2017+-+1er+tour");
        getFromAPI("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste&refine.libelle_du_scrutin=L%C3%A9gislatives+2017+-+2%C3%A8me+tour");
        getFromAPI("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste&refine.libelle_du_scrutin=Pr%C3%A9sidentielle+2017+-+2eme+tour");
        getFromAPI("https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux&rows=-1&facet=libelle_du_scrutin&facet=numero_d_arrondissement_01_a_20&facet=numero_de_bureau_de_vote_000_a_999&facet=nom_du_candidat_ou_liste");
    }


}
