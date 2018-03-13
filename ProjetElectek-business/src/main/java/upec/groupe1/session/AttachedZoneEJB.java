/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import com.google.gson.Gson;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.VoteOffices;
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
    protected boolean isInPolyGon(AttachedZone area , Point x){
        Polygon polyArea = area.getCoodinate();
        return polyArea.contains(x);
    }
    protected AttachedZone findAttachedZone(Adresse adress){
        Map<String,Object> params = new HashMap();
        params.put("arr", adress.getArr());
        List<AttachedZone> list = findNamedQuery("AttachedZone.findByArrondissement", params, AttachedZone.class);
        for (AttachedZone attachedZone : list) {
            if(isInPolyGon(attachedZone, adress.getGeoPoint()))
                return attachedZone;
        }
        return null;
    }

    
    public void create() {
        
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014&rows=-1");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            try{
            for(Map<String,Object> m : records){
                AttachedZone zone = new AttachedZone();
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                Double arr = (Double) ms.get("arrondisse");
                zone.setArr(arr.intValue());
                Double num_bv = (Double) ms.get("num_bv");
                zone.setNumber(num_bv.intValue());
                List<Double> lp =  (List<Double>) ms.get("geo_point_2d");
                System.err.println(lp);
                Point point = new Point(lp.get(0).intValue(), lp.get(1).intValue());
                zone.setGeoPoint(point);
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
                zone.setCoodinate(p);
                VoteOffices voteOffice = getVoteOfficeFromAttachedZone(zone);
                zone.setVoteOffice(voteOffice);
                System.err.println("DONE !!!");
                super.create(zone); 
            }
            }catch(NullPointerException|ClassCastException np){
                            
                }
           
        
          
    }
}
