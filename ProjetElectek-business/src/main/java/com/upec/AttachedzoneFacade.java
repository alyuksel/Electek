/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upec;

import com.upec.entities.Attachedzone;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drajasin
 */
@Stateless
public class AttachedzoneFacade extends AbstractFacade<Attachedzone> {

    @PersistenceContext(unitName = "com.upec_ProjetElectek")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttachedzoneFacade() {
        super(Attachedzone.class);
    }
    
}
