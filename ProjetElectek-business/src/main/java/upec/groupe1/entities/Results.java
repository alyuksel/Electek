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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author adam
 */
@Entity
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResults;

    @OneToOne
    @JoinColumn(name="idVoteOffice")
    private VoteOffices voteOffice;
    
    @Column
    private String caption;
    
    @Column
    private String candidate;
    
    @Column
    private Long numberOfVotes;

    public Long getIdResults() {
        return idResults;
    }

    public VoteOffices getIdVoteOffice() {
        return voteOffice;
    }


    public String getCaption() {
        return caption;
    }

    public String getCandidate() {
        return candidate;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setIdResults(Long idResults) {
        this.idResults = idResults;
    }

    public void setIdVoteOffice(VoteOffices VoteOffice) {
        this.voteOffice = VoteOffice;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
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
