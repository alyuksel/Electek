/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.affine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import upec.groupe1.entities.Results;

/**
 *
 * @author AMM
 */
public class AffineBV {
    private Map<String,Long> candidateScore = new HashMap<>();
    private Long numberOfVote;
    public AffineBV(Results r) {
        numberOfVote = r.getNbExprime();
    }
    public void addCandidate(Results r){
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
        if (candidateScore.containsKey(candidate)){
            return candidateScore.get(candidate)*100/numberOfVote;
        }else
            return Long.valueOf(0);
    }
    public String getLast(){
        if (candidateScore.isEmpty())
            return "Aucun candidat pour ce bureau de vote";
        Long last = candidateScore.values().stream().sorted().findFirst().get();
        return shearchCandidatebyScore(last);
    }
    public String getFirst(){

        if (candidateScore.isEmpty())
            return "Aucun candidat pour ce bureau de vote";
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
