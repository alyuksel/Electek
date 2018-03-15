/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import com.google.gson.Gson;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.session.Exceptions.NotFoundException;
import upec.groupe1.tools.Tools;

/**
 *
 * @author drajasin
 */
@Stateless
public class AdresseEJB extends ConcretEJB<Adresse>{

    @EJB
    protected AttachedZoneEJB attachedZoneEJB;
    
    
    public void create() {
        
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=adresse_paris&rows=20");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
            for(Map<String,Object> m : records){
                try {
                Adresse a = new Adresse();
                Map<String,Object> ms = (Map<String,Object>) m.get("fields");
                String address = (String)ms.get("l_adr");

                a.setStreetName(address);
                Double streetnum = (Double) ms.get("n_voie");

                a.setStreetNum(streetnum.intValue());
                Map<String,Object> geo = (Map<String,Object>) ms.get("geom");
                List<Double> li = (List<Double>) geo.get("coordinates");
         
                Double[] l =  new Double[2];
                l[0]=li.get(0);
                l[1]=li.get(1);
                a.setGeoPoint(l);
                Point2D point = new Point2D.Double(l[0], l[1]);
                Double arr = (Double) ms.get("c_ar");

                int arrInt = 0;
                try{
                    arrInt = arr.intValue();
                }catch(NullPointerException e){ 
                    }
                a.setArr(arrInt);
                try {
                    AttachedZone aZ = getAttachedZoneFromAdress(a,point);

                    a.setAttachedZone(aZ);
                }catch(NotFoundException e){
                    System.err.println(e.getMessage());
                }
                super.create(a);
                System.err.println("Done !!!");
                }catch(NullPointerException e){ 
                    System.err.println("NUlL");
                    }
    }
    }

    private AttachedZone getAttachedZoneFromAdress(Adresse a,Point2D point) throws NotFoundException{
        return attachedZoneEJB.findAttachedZone(a,point);
    }
    
}
