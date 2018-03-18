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
    private int voteNumber;
    private final int percent;
    private int total;

    public Score(int voteNumber,int total) {
        this.voteNumber = voteNumber;
        this.total = total;
        this.percent = (voteNumber * total) / 100;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public int getPercent() {
        return percent;
    }

}
