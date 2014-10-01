package edu.chl.hajo.shop.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here 
 * (Create, Read, Update, Delete = CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 * @param <T> Any type
 * @param <K> Key
 */
public abstract class AbstractDAO<T, K> implements IDAO<T, K> {

    // Emulate a database
    private final Class<T> clazz;
    
    protected abstract EntityManager getEntityManager();
    
    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }
    @Override
    public void create(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void delete(K id) {
       T t = getEntityManager().getReference(clazz, id);
        getEntityManager().remove(t);
    }

    @Override
    public void update(T t) {
        getEntityManager().merge(t);
       
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findRange(int first, int n) {
        // From inclusive, to exclusive, no checks here
        
        return null;
       
    }

    @Override
    public int count() {
        return 0;
        
    }
}
