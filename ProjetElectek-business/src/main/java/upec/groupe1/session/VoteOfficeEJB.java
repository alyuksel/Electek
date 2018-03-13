/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import java.util.List;
import upec.groupe1.entities.VoteOffices;

/**
 *
 * @author drajasin
 */
public class VoteOfficeEJB extends ConcretEJB<VoteOffices> {
    
    public VoteOffices findVoteOffice(int numBV, int arr){
        List<VoteOffices> result = em.createNamedQuery("VoteOffices.findByNumber",VoteOffices.class)
                .setParameter("number", arr+"-"+numBV)
                .getResultList();
        if(! result.isEmpty()) {
            System.out.println("Count LIST" + result.size());
            return result.get(0);
        }
        return null;
    }
    
    public List<VoteOffices> getVoteOfficesByArrondissement() {
        return findNamedQuery("VoteOffices.findAll");
    }
}