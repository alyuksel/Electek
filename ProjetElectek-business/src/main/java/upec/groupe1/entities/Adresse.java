/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.entities;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author adam
 */
@Entity
@NamedQueries({@NamedQuery(name = "Adresse.findId", query = "SELECT a FROM Adresse a WHERE a.idAdresses = :idAdresses"),
               @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
               @NamedQuery(name = "Adresse.findNumberAndStreetName", query = "SELECT a FROM Adresse a WHERE a.streetNum =:streetNum and a.streetName = UPPER(:streetName)")
})
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

    @Column(name="geo_point",length = 1000)
    private Double[] geoPoint;

    public Point2D getGeoPoint() {
        List<Double> l = Arrays.asList(geoPoint);
        return new Point2D.Double(l.get(0),l.get(1));
    }

    public void setGeoPoint(Double[] geoPoint) {
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
