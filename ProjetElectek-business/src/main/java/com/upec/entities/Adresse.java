/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upec.entities;

import java.io.Serializable;
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
@Table(name = "adresse", catalog = "electeck", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a")
    , @NamedQuery(name = "Adresse.findByIdAdresses", query = "SELECT a FROM Adresse a WHERE a.idAdresses = :idAdresses")
    , @NamedQuery(name = "Adresse.findByArr", query = "SELECT a FROM Adresse a WHERE a.arr = :arr")
    , @NamedQuery(name = "Adresse.findByStreetName", query = "SELECT a FROM Adresse a WHERE a.streetName = :streetName")
    , @NamedQuery(name = "Adresse.findByStreetNum", query = "SELECT a FROM Adresse a WHERE a.streetNum = :streetNum")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdresses", nullable = false)
    private Long idAdresses;
    @Column(name = "Arr")
    private Integer arr;
    @Size(max = 255)
    @Column(name = "streetName", length = 255)
    private String streetName;
    @Column(name = "streetNum")
    private Integer streetNum;
    @JoinColumn(name = "idAttachedZone", referencedColumnName = "idAttachedVoteOffices")
    @ManyToOne
    private Attachedzone idAttachedZone;

    public Adresse() {
    }

    public Adresse(Long idAdresses) {
        this.idAdresses = idAdresses;
    }

    public Long getIdAdresses() {
        return idAdresses;
    }

    public void setIdAdresses(Long idAdresses) {
        this.idAdresses = idAdresses;
    }

    public Integer getArr() {
        return arr;
    }

    public void setArr(Integer arr) {
        this.arr = arr;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(Integer streetNum) {
        this.streetNum = streetNum;
    }

    public Attachedzone getIdAttachedZone() {
        return idAttachedZone;
    }

    public void setIdAttachedZone(Attachedzone idAttachedZone) {
        this.idAttachedZone = idAttachedZone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresses != null ? idAdresses.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresses == null && other.idAdresses != null) || (this.idAdresses != null && !this.idAdresses.equals(other.idAdresses))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upec.entities.Adresse[ idAdresses=" + idAdresses + " ]";
    }
    
}
