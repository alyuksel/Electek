/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.affine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import upec.groupe1.entities.Results;

/**
 *
 * @author AMM
 */
public class AffineBV {
    private Map<String,Long> candidateScore = new HashMap<>();
    private Long numberOfVote;
    public AffineBV(Results r) {
        System.out.println("creation de affineBV");
        numberOfVote = r.getNbExprime();
    }
    public void addCandidate(Results r){
        System.out.println("ajout d'un resultat dans l'affineBV");
        String key = r.getCandidateFN()+"_"+r.getCandidateLN();
        if(candidateScore.containsKey(key)){
            Long v = candidateScore.get(key);
            candidateScore.put(key, v+r.getNbVoie());
            numberOfVote = numberOfVote + r.getNbExprime();
        }else{
            candidateScore.put(key, r.getNbVoie());
            numberOfVote = numberOfVote + r.getNbExprime();
        }
    }
    
    public Long purcent(String candidate){
        return candidateScore.get(candidate)*100/numberOfVote;
    }
    public String getLast(){
        Long last = candidateScore.values().stream().sorted().findFirst().get();
        return shearchCandidatebyScore(last);
    }
    public String getFirst(){
        Long first = candidateScore.values().stream().sorted(Comparator.reverseOrder()).findFirst().get();
        return shearchCandidatebyScore(first);
    }
    
    private String shearchCandidatebyScore(Long score){
        for (String candidate : candidateScore.keySet()){
            if (candidateScore.get(candidate).equals(score))
                return candidate;
       
    }
        return "none";
    }

}
