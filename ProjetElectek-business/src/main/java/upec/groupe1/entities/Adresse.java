/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.entities;

import java.awt.Point;
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
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdresses;
    
    @OneToOne
    @JoinColumn(name="idAttachedZone")
    private AttachedZone attachedZone;

    
    @Column
    private Integer streetNum;

    @Column
    private String streetName;
    
    @Column
    private Integer Arr;

    @Column(name="geo_point")
    private Point geoPoint;

    public Point getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(Point geoPoint) {
        this.geoPoint = geoPoint;
    }
    
    public AttachedZone getAttachedZone() {
        return attachedZone;
    }

    public void setAttachedZone(AttachedZone attachedZone) {
        this.attachedZone = attachedZone;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setArr(Integer Arr) {
        this.Arr = Arr;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getArr() {
        return Arr;
    }

    public Long getIdAdresses() {
        return idAdresses;
    }

    public void setIdAdresses(Long idAdresses) {
        this.idAdresses = idAdresses;
    }    
    
    public Integer getStreetNum(){
        return streetNum;
    }
    
    public void setStreetNum(Integer streetNum){
        this.streetNum = streetNum;
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
        return "upec.groupe1.electek.model.Adresse[ id=" + idAdresses + " ]";
    }
    
}
