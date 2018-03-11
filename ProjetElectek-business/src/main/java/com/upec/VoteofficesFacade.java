/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upec;

import com.upec.entities.Voteoffices;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drajasin
 */
@Stateless
public class VoteofficesFacade extends AbstractFacade<Voteoffices> {

    @PersistenceContext(unitName = "com.upec_ProjetElectek")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void createAtestvoteoffice() {
        Voteoffices v = new Voteoffices();
        v.setAdress("AN ADRESS");
        v.setCaption("dsd");
        v.setCp("fdsfds");
        v.setNumber("dsdds");
        System.out.println("CREATION DE VITE");
        create(v);
    }

    public String printVoteOffice() {
        StringBuilder s = new StringBuilder("PRINT VOTE ");
        s.append("OFIICE");
        for (Voteoffices voteoffices : findAll()) {
            s.append(voteoffices.toString());
        }
        return s.toString();
    }

    public VoteofficesFacade() {
        super(Voteoffices.class);
    }

}
