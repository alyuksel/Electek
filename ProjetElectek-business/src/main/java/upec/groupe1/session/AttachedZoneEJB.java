/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import com.google.gson.Gson;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;

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
    protected boolean isInPolyGon(AttachedZone area , Point2D x){
        Area polyArea = area.getCoodinate();
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
                Double[][][] ll = (Double[][][]) ml.get("coordinates");
                zone.setCoodinate(ll);
                VoteOffices voteOffice = getVoteOfficeFromAttachedZone(zone);
                zone.setVoteOffice(voteOffice);
                System.out.println("DONE !!!");
                super.create(zone); 
                }catch(NullPointerException|ClassCastException np){
                            
                }
            }
            
           
        
          
    }
}
