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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author drajasin
 */
@Entity
@Table(name = "attachedzone", catalog = "electeck", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attachedzone.findAll", query = "SELECT a FROM Attachedzone a")
    , @NamedQuery(name = "Attachedzone.findByIdAttachedVoteOffices", query = "SELECT a FROM Attachedzone a WHERE a.idAttachedVoteOffices = :idAttachedVoteOffices")
    , @NamedQuery(name = "Attachedzone.findByNumber", query = "SELECT a FROM Attachedzone a WHERE a.number = :number")})
public class Attachedzone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttachedVoteOffices", nullable = false)
    private Long idAttachedVoteOffices;
    @Lob
    @Column(name = "coodinate")
    private byte[] coodinate;
    @Lob
    @Column(name = "geo_point")
    private byte[] geoPoint;
    @Column(name = "number")
    private Integer number;
    @JoinColumn(name = "idVoteOffice", referencedColumnName = "idVoteOffices")
    @ManyToOne
    private Voteoffices idVoteOffice;
    @OneToMany(mappedBy = "idAttachedZone")
    private Collection<Adresse> adresseCollection;

    public Attachedzone() {
    }

    public Attachedzone(Long idAttachedVoteOffices) {
        this.idAttachedVoteOffices = idAttachedVoteOffices;
    }

    public Long getIdAttachedVoteOffices() {
        return idAttachedVoteOffices;
    }

    public void setIdAttachedVoteOffices(Long idAttachedVoteOffices) {
        this.idAttachedVoteOffices = idAttachedVoteOffices;
    }

    public byte[] getCoodinate() {
        return coodinate;
    }

    public void setCoodinate(byte[] coodinate) {
        this.coodinate = coodinate;
    }

    public byte[] getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(byte[] geoPoint) {
        this.geoPoint = geoPoint;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Voteoffices getIdVoteOffice() {
        return idVoteOffice;
    }

    public void setIdVoteOffice(Voteoffices idVoteOffice) {
        this.idVoteOffice = idVoteOffice;
    }

    @XmlTransient
    public Collection<Adresse> getAdresseCollection() {
        return adresseCollection;
    }

    public void setAdresseCollection(Collection<Adresse> adresseCollection) {
        this.adresseCollection = adresseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttachedVoteOffices != null ? idAttachedVoteOffices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attachedzone)) {
            return false;
        }
        Attachedzone other = (Attachedzone) object;
        if ((this.idAttachedVoteOffices == null && other.idAttachedVoteOffices != null) || (this.idAttachedVoteOffices != null && !this.idAttachedVoteOffices.equals(other.idAttachedVoteOffices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upec.entities.Attachedzone[ idAttachedVoteOffices=" + idAttachedVoteOffices + " ]";
    }
    
}
