/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.dto;

/**
 *
 * @author alpi
 */
public class Score {
    private Candidate candidate;
    private long voteNumber;
    private final double percent;
    private long total;

    public Score(Candidate candidate, long voteNumber,long total) {
        this.candidate = candidate;
        this.voteNumber = voteNumber;
        this.total = total;
        System.out.println("total : " + total + " vote : " +voteNumber);
        this.percent = (voteNumber * 100) / total;
    }

    public long getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public double getPercent() {
        return percent;
    }

    public long getTotal() {
        return total;
    }

    public Candidate getCandidate(){
        return candidate;
    }

}
