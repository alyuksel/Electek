/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.AttachedZone;

/**
 *
 * @author drajasin
 */
@Stateless
public class AdresseEJB extends ConcretEJB<Adresse>{

    @EJB
    protected AttachedZoneEJB attachedZoneEJB;
    
    @Override
    public void create(Adresse a) {
        AttachedZone aZ = getAttachedZoneFromAdress(a);
        a.setAttachedZone(aZ);
        super.create(a);
    }

    private AttachedZone getAttachedZoneFromAdress(Adresse a) {
        return attachedZoneEJB.findAttachedZone(a);
    }
    
}
