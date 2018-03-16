/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import java.util.List;
import javax.ejb.Stateless;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.Exceptions.NotFoundException;

/**
 *
 * @author drajasin
 */
@Stateless
public class VoteOfficeEJB extends ConcretEJB<VoteOffices> {
    
    public VoteOffices findVoteOffice(int numBV, int arr) throws NotFoundException{
        System.out.println(arr+"-"+numBV);
        List<VoteOffices> result = em.createNamedQuery("VoteOffices.findByNumber",VoteOffices.class)
                .setParameter("number", arr+"-"+numBV)
                .getResultList();
        if(! result.isEmpty()) {
            System.out.println("Count LIST" + result.size());
            return result.get(0);
        }
        throw new NotFoundException("VoteOfficesNotFound");
    }
    
    public List<VoteOffices> findVotesOfficesByNumber(int number){    
        return em.createNamedQuery("VoteOffices.findByNV",VoteOffices.class)
                .setParameter("number","%-"+number)
                .getResultList();
    }
    
    public List<VoteOffices> getVoteOfficesByArrondissement() {
        return findNamedQuery("VoteOffices.findAll");
    }
}