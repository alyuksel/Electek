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
            System.out.println("Count LIST" + result.size());
            return result.get(0);
        }
        return null;
    }
    
    public Map<String, List<VoteOffices>> getVoteOfficesByArrondissement() {
        List<VoteOffices> voteOfficeses = findNamedQuery("VoteOffices.findAll");
        Map<String, List<VoteOffices>> mapVoteOffices = voteOfficeses.stream().collect(Collectors.groupingBy((VoteOffices vo) -> {
            return vo.getNumber().split("-")[0];
        }));
        mapVoteOffices.forEach((k, v) -> {
            v.stream().distinct().collect(Collectors.toList());
        });
        return mapVoteOffices;
    }
}