/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author adam
 */
@Entity
@NamedQueries({@NamedQuery(name = "Results.listCandidates", query = "SELECT distinct r FROM Results r ORDER BY r.candidateFN "),
        @NamedQuery(name= "Results.getResults" ,query = "SELECT r FROM Results r" ),
        @NamedQuery(name= "Results.getResultsByID" , query = "SELECT r FROM Results r WHERE r.idResults = :id"),
        @NamedQuery(name= "Results.getResultsByBV" , query = "SELECT r FROM Results r WHERE r.caption = :caption and r.turn = :turn and r.yearEl = :year"),
        @NamedQuery(name= "Results.getResultsByOrder" , query = "SELECT r FROM Results r WHERE r.caption = :caption and r.yearEl = :year ")
})
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResults;

    
    
    @Column
    private String caption;
    
    @Column
    private String candidateFN;
   
     
    @Column
    private String candidateLN;

    @Column
    private String yearEl;
    
    @Column
    private String turn;
    
    @Column
    private Long nbVotants;
    
    @Column
    private Long nbExprime;
    
    @Column 
    private Long nbVoie;

    @Column 
    private Long numBV;
    
    

    public Long getIdResults() {
        return idResults;
    }

    public String getCaption() {
        return caption;
    }

    public String getCandidateFN() {
        return candidateFN;
    }

     public String getCandidateLN() {
        return candidateLN;
    }

    public String getYearEl() {
        return yearEl;
    }

    public String getTurn() {
        return turn;
    }

    public Long getNbVotants() {
        return nbVotants;
    }

    public Long getNbExprime() {
        return nbExprime;
    }

    public Long getNbVoie() {
        return nbVoie;
    }

    public Long getNumBV() {
        return numBV;
    }

    public void setYearEl(String yearEl) {
        this.yearEl = yearEl;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void setNbVotants(Long nbVotants) {
        this.nbVotants = nbVotants;
    }

    public void setNbExprime(Long nbExprime) {
        this.nbExprime = nbExprime;
    }

    public void setNbVoie(Long nbVoie) {
        this.nbVoie = nbVoie;
    }
     
     
     
    public void setIdResults(Long idResults) {
        this.idResults = idResults;
    }


    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCandidateFN(String candidateFN) {
        this.candidateFN = candidateFN;
    }

    public void setCandidateLN(String candidateLN) {
        this.candidateLN = candidateLN;
    }

    public void setNumBV(Long numBV) {
        this.numBV = numBV;
    }
    
    public Long getPurcent(){
        return (nbVoie *100/nbExprime);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResults != null ? idResults.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Results)) {
            return false;
        }
        Results other = (Results) object;
        if ((this.idResults == null && other.idResults != null) || (this.idResults != null && !this.idResults.equals(other.idResults))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "upec.groupe1.electek.model.Results[ id=" + idResults + " ]";
    }
    
}
