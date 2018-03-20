

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package upec.groupe1.entities;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author adam
 */
@Entity
@Table(name = "voteoffices")
@NamedQueries({@NamedQuery(name = "VoteOffices.findByNumber", query = "SELECT v FROM VoteOffices v WHERE v.number = :number"),
               @NamedQuery(name = "VoteOffices.findAll", query = "SELECT v FROM VoteOffices v"),
               @NamedQuery(name = "VoteOffices.deleteAll", query = "delete from VoteOffices")
})
public class VoteOffices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVoteOffices")
    @Basic(optional = false)
    private Long idVoteOffices;

    @Column(name = "number")
    private String number;

    @Column(name = "caption")
    private String caption;

    @Column(name = "adress")
    private String adress;

    @Column(name="cp")
    private String postalCode;
    
    @Column(name="geo_point",length = 1000)
    private Double[] geoPoint;

    public Point2D getGeoPoint() {
        List<Double> l = Arrays.asList(geoPoint);
        return new Point2D.Double(l.get(0),l.get(1));
    }

    public void setGeoPoint(Double[] geoPoint) {
        this.geoPoint = geoPoint;
    }
    
    public void setIdVoteOffices(Long idVoteOffices) {
        this.idVoteOffices = idVoteOffices;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public Long getIdVoteOffices() {
        return idVoteOffices;
    }

    public String getNumber() {
        return number;
    }

    public String getCaption() {
        return caption;
    }

    public String getAdress() {
        return adress;
    }

    public String getPostalCode() {
        return postalCode;
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
        if (!(object instanceof VoteOffices)) {
            return false;
        }
        VoteOffices other = (VoteOffices) object;
        if ((this.idVoteOffices == null && other.idVoteOffices != null) || (this.idVoteOffices != null && !this.idVoteOffices.equals(other.idVoteOffices))) {
            return false;
        }
        return true;
    }

    public String extractBvNum(){
        return number.split("-")[1];
    }

    public String extractArr(){
        return number.split("-")[0];
    }

    @Override
    public String toString() {
        return "upec.groupe1.electek.model.VoteOffices[ id=" + idVoteOffices + " ]";
    }

}
