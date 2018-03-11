/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upec.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author drajasin
 */
@Entity
@Table(name = "voteoffices", catalog = "electeck", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voteoffices.findAll", query = "SELECT v FROM Voteoffices v")
    , @NamedQuery(name = "Voteoffices.findByIdVoteOffices", query = "SELECT v FROM Voteoffices v WHERE v.idVoteOffices = :idVoteOffices")
    , @NamedQuery(name = "Voteoffices.findByAdress", query = "SELECT v FROM Voteoffices v WHERE v.adress = :adress")
    , @NamedQuery(name = "Voteoffices.findByCaption", query = "SELECT v FROM Voteoffices v WHERE v.caption = :caption")
    , @NamedQuery(name = "Voteoffices.findByCp", query = "SELECT v FROM Voteoffices v WHERE v.cp = :cp")
    , @NamedQuery(name = "Voteoffices.findByNumber", query = "SELECT v FROM Voteoffices v WHERE v.number = :number")})
public class Voteoffices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVoteOffices", nullable = false)
    private Long idVoteOffices;
    @Size(max = 255)
    @Column(name = "adress", length = 255)
    private String adress;
    @Size(max = 255)
    @Column(name = "caption", length = 255)
    private String caption;
    @Size(max = 255)
    @Column(name = "cp", length = 255)
    private String cp;
    @Size(max = 255)
    @Column(name = "number", length = 255)
    private String number;
    @OneToMany(mappedBy = "idVoteOffice")
    private Collection<Attachedzone> attachedzoneCollection;
    @OneToMany(mappedBy = "idVoteOffice")
    private Collection<Results> resultsCollection;

    public Voteoffices() {
    }

    public Voteoffices(Long idVoteOffices) {
        this.idVoteOffices = idVoteOffices;
    }

    public Long getIdVoteOffices() {
        return idVoteOffices;
    }

    public void setIdVoteOffices(Long idVoteOffices) {
        this.idVoteOffices = idVoteOffices;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlTransient
    public Collection<Attachedzone> getAttachedzoneCollection() {
        return attachedzoneCollection;
    }

    public void setAttachedzoneCollection(Collection<Attachedzone> attachedzoneCollection) {
        this.attachedzoneCollection = attachedzoneCollection;
    }

    @XmlTransient
    public Collection<Results> getResultsCollection() {
        return resultsCollection;
    }

    public void setResultsCollection(Collection<Results> resultsCollection) {
        this.resultsCollection = resultsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoteOffices != null ? idVoteOffices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voteoffices)) {
            return false;
        }
        Voteoffices other = (Voteoffices) object;
        if ((this.idVoteOffices == null && other.idVoteOffices != null) || (this.idVoteOffices != null && !this.idVoteOffices.equals(other.idVoteOffices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upec.entities.Voteoffices[ idVoteOffices=" + idVoteOffices + " ]";
    }
    
}
