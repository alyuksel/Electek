/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import upec.groupe1.entities.AttachedZone;
import upec.groupe1.entities.VoteOffices;

/**
 *
 * @author drajasin
 */
@Stateless
public class AttachedZoneEJB extends ConcretEJB<AttachedZone> {
    //@EJB
    //protected VoteOfficeEJB voteOfficeEJB;
    
    protected  VoteOffices getVoteOfficeFromAttachedZone(AttachedZone zone){
        int numBV = zone.getNumber();
        int arr   = zone.getArr();
        return null;//voteOfficeEJB.findVoteOffice(numBV,arr);
    }
    
    @Override
    public void create(AttachedZone zone) {
        VoteOffices voteOffice = getVoteOfficeFromAttachedZone(zone);
        zone.setVoteOffice(voteOffice);
        super.create(zone);
        
    }
    
}
