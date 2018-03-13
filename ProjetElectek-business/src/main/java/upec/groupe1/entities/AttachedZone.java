/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.entities;

import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
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

@NamedQueries({@NamedQuery(name = "AttachedZone.findByNumber", query = "SELECT a FROM AttachedZone a WHERE a.number = :number"),
               @NamedQuery(name = "AttachedZone.findAll", query = "SELECT a FROM AttachedZone a"),
               @NamedQuery(name = "AttachedZone.findByArrondissement", query = "SELECT a FROM AttachedZone a WHERE a.arr = :arr")
})
public class AttachedZone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttachedVoteOffices;
    
    @OneToOne
    @JoinColumn(name="idVoteOffice")
    private VoteOffices voteOffice;
    
    @Column
    private Integer number;
    
    @Column
    private Integer arr;
    @Column(name="geo_point",length = 1000)
    private Point geoPoint;
    
    @Column(length = 1000)
    private Polygon coodinate;

    public Long getIdAttachedVoteOffices() {
        return idAttachedVoteOffices;
    }

    public VoteOffices getVoteOffice() {
        return voteOffice;
    }

    
    public Integer getNumber() {
        return number;
    }
    
    public Integer getArr() {
        return arr;
    }
    
    public Point getGeoPoint() {
        return geoPoint;
    }

    public Polygon getCoodinate() {
        return coodinate;
    }

    public void setIdAttachedVoteOffices(Long idAttachedVoteOffices) {
        this.idAttachedVoteOffices = idAttachedVoteOffices;
    }

    public void setVoteOffice(VoteOffices voteOffice) {
        this.voteOffice = voteOffice;
    }

    public void setArr(Integer arr) {
        this.arr=arr;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setGeoPoint(Point geoPoint) {
        this.geoPoint = geoPoint;
    }

    public void setCoodinate(Polygon coodinate) {
        this.coodinate = coodinate;
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
        if (!(object instanceof AttachedZone)) {
            return false;
        }
        AttachedZone other = (AttachedZone) object;
        if ((this.idAttachedVoteOffices == null && other.idAttachedVoteOffices != null) || (this.idAttachedVoteOffices != null && !this.idAttachedVoteOffices.equals(other.idAttachedVoteOffices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "upec.groupe1.electek.model.AttachedZone[ id=" + idAttachedVoteOffices + " ]";
    }
    
}
