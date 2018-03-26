/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.affine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import upec.groupe1.entities.Results;

/**
 *
 * @author AMM
 */
public class AffineBV {
    private Map<String,Long> candidateScore;
    private Map<String,Long> candidateExprime;
    public AffineBV(Results r) {
        candidateScore = new HashMap<>();
        candidateExprime = new HashMap<>();
        String key = r.getCandidateFN()+"_"+r.getCandidateLN();
        candidateScore.put(key, r.getNbVoie());
        candidateExprime.put(key,r.getNbExprime());
    }
    public void addCandidate(Results r){
        String key = r.getCandidateFN()+"_"+r.getCandidateLN();
        if(candidateScore.containsKey(key)){
            Long v = candidateScore.get(key);
            Long e = candidateExprime.get(key);
            candidateScore.put(key, v+r.getNbVoie());
            candidateExprime.put(key, e+r.getNbExprime());
        }else{
            candidateScore.put(key, r.getNbVoie());
            candidateExprime.put(key, r.getNbExprime());
        }
    }
    
    public Double purcent(String candidate){
        if (candidateScore.containsKey(candidate)){
            double temp = (candidateScore.get(candidate)*100.0)/candidateExprime.get(candidate);
            BigDecimal bd = new BigDecimal(temp);
            bd = bd.setScale(2, RoundingMode.HALF_UP);      
            return  bd.doubleValue();
        }else
            return Double.valueOf(0);
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
    
    public Map<String,Long> getMapScores(){
        return candidateScore;
    }

}
