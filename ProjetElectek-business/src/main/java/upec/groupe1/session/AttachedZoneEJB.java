/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.VoteOffices;

/**
 *
 * @author drajasin
 */
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
    
    @Override
    public void create(AttachedZone zone) {
        VoteOffices voteOffice = getVoteOfficeFromAttachedZone(zone);
        zone.setVoteOffice(voteOffice);
        super.create(zone);   
    }
}
