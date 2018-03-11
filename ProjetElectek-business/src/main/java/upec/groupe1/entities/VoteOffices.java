

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


/**
 *
 * @author adam
 */
@Entity
public class VoteOffices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoteOffices;
    
    @Column
    private String number;
    
    @Column
    private String caption;
    
    @Column
    private String adress;
    
    @Column(name="cp")
    private String postalCode;

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

    @Override
    public String toString() {
        return "upec.groupe1.electek.model.VoteOffices[ id=" + idVoteOffices + " ]";
    }
    
}
