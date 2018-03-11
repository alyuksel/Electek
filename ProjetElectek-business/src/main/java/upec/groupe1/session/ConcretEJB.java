/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import upec.groupe1.session.Exceptions.NotFoundException;


/**
 *
 * @author adam
 */
@LocalBean
@Stateless
public class ConcretEJB<T> implements genericDAOImplLocal<T>{
    @PersistenceContext(name="electek-persist")
    private EntityManager em ;
    
    
    /**
     * insere un Resultat dans la table 
     * @param t 
     */
    @Override
    public void create(T t) {
        em.persist(t);
    }
    
    @Override
    public T find(Object id) throws NotFoundException{
       T res =  (T) em.find(Object.class ,id);
       if(res==null) throw new NotFoundException("Result not found");
       return res;     
    }

    @Override
    public void delete( Object id) throws NotFoundException{
       T ref = find(id);
       em.remove(ref);
    }

    @Override
    public List<T> getAllData(String query, int returnlimit) {
        Query q = em.createQuery(query);
        if(returnlimit >0) q.setMaxResults(returnlimit);
        return q.getResultList();
    }

    @Override
    public void update(T t) {
        em.merge(t);
    }
}
