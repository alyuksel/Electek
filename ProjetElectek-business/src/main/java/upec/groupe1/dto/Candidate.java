/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.dto;

/**
 *
 * @author alpi
 */
public class Candidate {
    private String nom;
    private String prenom;
    private String caption;
    
    public Candidate(String nom, String prenom, String caption) {
        this.nom = nom;
        this.prenom = prenom;
        this.caption = caption;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public String getFullName (){
        return nom+"_"+prenom;
    }

    @Override
    public int hashCode() {
        return nom.hashCode() + prenom.hashCode() + caption.hashCode();
    }
    
    
    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj.hashCode() == this.hashCode());
    }
}
