/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import upec.groupe1.entities.VoteOffices;

/**
 *
 * @author drajasin
 */
@Stateless
public class VoteOfficeEJB extends ConcretEJB<VoteOffices> {
    
    public VoteOffices findVoteOffice(int numBV, int arr){
        List<VoteOffices> result = em.createNamedQuery("VoteOffices.findByNumber",VoteOffices.class)
                .setParameter("number", arr+"-"+numBV)
                .getResultList();
        if(! result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    
    public Map<String, List<VoteOffices>> getVoteOfficesByArrondissement() {
        List<VoteOffices> voteOffices = findNamedQuery("VoteOffices.findAll");
        return voteOffices.stream().collect(Collectors.groupingBy(VoteOffices::extractArr));  
    }
}