/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upec.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drajasin
 */
@Entity
@Table(name = "results", catalog = "electeck", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Results.findAll", query = "SELECT r FROM Results r")
    , @NamedQuery(name = "Results.findByIdResults", query = "SELECT r FROM Results r WHERE r.idResults = :idResults")
    , @NamedQuery(name = "Results.findByCandidate", query = "SELECT r FROM Results r WHERE r.candidate = :candidate")
    , @NamedQuery(name = "Results.findByCaption", query = "SELECT r FROM Results r WHERE r.caption = :caption")
    , @NamedQuery(name = "Results.findByNumberOfVotes", query = "SELECT r FROM Results r WHERE r.numberOfVotes = :numberOfVotes")})
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResults", nullable = false)
    private Long idResults;
    @Size(max = 255)
    @Column(name = "candidate", length = 255)
    private String candidate;
    @Size(max = 255)
    @Column(name = "caption", length = 255)
    private String caption;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numberOfVotes", precision = 19, scale = 2)
    private BigDecimal numberOfVotes;
    @JoinColumn(name = "idVoteOffice", referencedColumnName = "idVoteOffices")
    @ManyToOne
    private Voteoffices idVoteOffice;

    public Results() {
    }

    public Results(Long idResults) {
        this.idResults = idResults;
    }

    public Long getIdResults() {
        return idResults;
    }

    public void setIdResults(Long idResults) {
        this.idResults = idResults;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public BigDecimal getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(BigDecimal numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Voteoffices getIdVoteOffice() {
        return idVoteOffice;
    }

    public void setIdVoteOffice(Voteoffices idVoteOffice) {
        this.idVoteOffice = idVoteOffice;
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
        return "com.upec.entities.Results[ idResults=" + idResults + " ]";
    }
    
}
