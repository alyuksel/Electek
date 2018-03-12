/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import javax.ejb.Stateless;
import upec.groupe1.entities.VoteOffices;

/**
 *
 * @author drajasin
 */
@Stateless
public class VoteOfficeEJB extends ConcretEJB<VoteOffices> {
    
    public VoteOffices findVoteOffice(int numBV, int arr){
        return em.createNamedQuery("VoteOffices.findByNumber",VoteOffices.class)
                .setParameter("number", arr+"-"+numBV)
                .getResultList().get(0);
    }
}
