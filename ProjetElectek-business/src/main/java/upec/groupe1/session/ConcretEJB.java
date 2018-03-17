/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import upec.groupe1.session.Exceptions.NotFoundException;


/**
 *
 * @author adam
 * @param <T>
 */
@LocalBean
public class ConcretEJB<T> implements genericDAOImplLocal<T>{
    @PersistenceContext(name="com.upec_ProjetElectek")
    protected EntityManager em ;
    
    
    /**
     * insere un Resultat dans la table 
     * @param t 
     */
    @Override
    public void create(T t) {
        em.persist(t);
        this.em.flush();
        this.em.refresh(t);
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
    
    @Override
    public List<T> findNamedQuery(String namedQuery) {
         return em.createNamedQuery(namedQuery).getResultList();
    }

    @Override
    public List<T> findNamedQuery(String namedQuery, Class<T> clazz) {
        return em.createNamedQuery(namedQuery, clazz).getResultList();
    }

    @Override
    public List<T> findNamedQuery(String namedQuery, Map<String, Object> params) {
        Query q = em.createNamedQuery(namedQuery);
        for(Entry<String, Object> entry: params.entrySet()){
            q.setParameter(entry.getKey(), entry.getValue());
        }
        return q.getResultList();
    }

    @Override
    public List<T> findNamedQuery(String namedQuery, Map<String, Object> params, Class<T> clazz) {
        Query q = em.createNamedQuery(namedQuery, clazz);
        for(Entry<String, Object> entry: params.entrySet()){
            q.setParameter(entry.getKey(), entry.getValue());
        }
        return q.getResultList();
    }
}
