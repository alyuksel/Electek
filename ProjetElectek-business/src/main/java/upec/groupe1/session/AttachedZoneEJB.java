/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import com.google.gson.Gson;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.Exceptions.NotFoundException;
import upec.groupe1.tools.Tools;

/**
 *
 * @author drajasin
 */
@Stateless
public class AttachedZoneEJB extends ConcretEJB<AttachedZone> {
    @EJB
    protected VoteOfficeEJB voteOfficeEJB;
    
    protected  VoteOffices getVoteOfficeFromAttachedZone(AttachedZone zone){
        int numBV = zone.getNumber();
        int arr   = zone.getArr();
        return voteOfficeEJB.findVoteOffice(numBV,arr);
    }
    // A TESTER le contains
    protected boolean isInPolyGon(AttachedZone area , Point2D x){
        Path2D polyArea = area.getCoodinate();
        return polyArea.contains(x);
    }
    public AttachedZone findAttachedZone(Adresse adress,Point2D point) throws NotFoundException{
        System.out.println(adress.getArr() + "  " + adress.getStreetNum());
        List<AttachedZone> result = em.createNamedQuery("AttachedZone.findByArrondissement",AttachedZone.class)
                .setParameter("arr",adress.getArr())
                .getResultList();
        if(! result.isEmpty()) {
            for (AttachedZone a : result){
                if(isInPolyGon(a, point)){
                    return a;
                }
            }
        }
        throw new NotFoundException("AttachedZoneNotFound");
    }

    
    public void create() {
        
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
           
            for(Map<String,Object> m : records){
                try{
                AttachedZone zone = new AttachedZone();
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                Double arr = (Double) ms.get("arrondisse");
                zone.setArr(arr.intValue());
                Double num_bv = (Double) ms.get("num_bv");
                zone.setNumber(num_bv.intValue());
                Map<String,Object> ml = (Map<String,Object>) ms.get("geo_shape");
                Area p = new Area();
                List<List<List<Double>>> coordinate =  (List<List<List<Double>>>) ml.get("coordinates");         
                Double[][][] tab = new Double[coordinate.size()][coordinate.get(0).size()][2];
                for (int i=0;i<coordinate.size();i++){
                    for(int j=0;j<coordinate.get(0).size();j++){
                        for(int k=0;k<2;k++){
                            tab[i][j][k]=coordinate.get(i).get(j).get(k);
                        }
                    }
                }
                zone.setCoodinate(tab);
                VoteOffices voteOffice = getVoteOfficeFromAttachedZone(zone);
                zone.setVoteOffice(voteOffice);
                System.out.println("DONE !!!");
                super.create(zone); 
                }catch(NullPointerException|ClassCastException np){
                            
                }
            }
            
           
        
          
    }
}
