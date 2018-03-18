/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;

import upec.groupe1.session.Exceptions.NotFoundException;


/**
 *
 * @author adam
 * @param <T>
 */
@Local
public interface genericDAOImplLocal<T> {
    public void create(T t);
    public  T find(Object id) throws NotFoundException; 
    public void delete(Object id) throws NotFoundException; 
    public void update(T t);
    public List<T> getAllData(String query,int returnlimit);
    
    public List<T> findNamedQuery(String namedQuery);
    public List<T> findNamedQuery(String namedQuery, Class<T> clazz);
    public List<T> findNamedQuery(String namedQuery, Map<String, Object> params);
    public List<T> findNamedQuery(String namedQuery, Map<String, Object> params, Class<T> clazz);
    
    
    public long count(Class<T> clazz);
}
