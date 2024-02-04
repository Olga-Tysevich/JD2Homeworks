package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.DAO;
import org.example.lesson9.utils.HibernateUtil;
import org.example.lesson9.utils.ReflectionManager;
import javax.persistence.EntityManager;

public class DAOImpl<T> implements DAO<T> {
    private EntityManager manager;

    @Override
    public T save(T object) {
        if (object != null) {
            Class<?> clazz = object.getClass();
            Object id = ReflectionManager.getId(object);
            startTransaction();
            if (id != null && manager.find(clazz, id) == null) {
                manager.persist(object);
            } else {
                manager.merge(object);
            }
            commit();
        }
        return object;
    }

    @Override
    public T update(T object) {
        return save(object);
    }

    @Override
    public T get(int id, Class<T> clazz) {
        startTransaction();
        T result = manager.find(clazz, id);
        commit();
        return result;
    }

    @Override
    public void delete(int id, Class<T> clazz) {
        startTransaction();
        T objectForDelete = manager.find(clazz, id);
        if (objectForDelete != null) {
            manager.remove(objectForDelete);
        }
        commit();
    }

    protected void startTransaction() {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
    }

    protected void commit() {
        if (manager != null && manager.getTransaction().isActive()) {
            manager.getTransaction().commit();
            manager.close();
        }
    }

    protected EntityManager getManager() {
        return manager != null? manager : HibernateUtil.getEntityManager();
    }

}
