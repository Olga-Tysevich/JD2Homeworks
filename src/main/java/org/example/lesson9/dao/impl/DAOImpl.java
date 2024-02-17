package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.DAO;
import org.example.lesson9.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.function.Supplier;

public abstract class DAOImpl<T> implements DAO<T> {
    private EntityManager manager = HibernateUtil.getEntityManager();
    private Supplier<T> currentMethod;

    protected abstract Class<T> getClazz();

    @Override
    public T save(T object) {
        currentMethod = () -> {
            manager.persist(object);
            return object;
        };
        return executeTransaction(currentMethod);
    }

    @Override
    public T update(T object) {
        currentMethod = () -> {
            manager.merge(object);
            return object;
        };
        return executeTransaction(currentMethod);
    }

    @Override
    public T get(int id) {
        currentMethod = () -> manager.find(getClazz(), id);
        return executeTransaction(currentMethod);
    }

    @Override
    public void delete(int id) {
        currentMethod = () -> {
            T objectForDelete = manager.find(getClazz(), id);
            if (objectForDelete != null) {
                manager.remove(objectForDelete);
            }
            return objectForDelete;
        };
        executeTransaction(currentMethod);
    }

    protected final Query createNativeQuery(String query, Class<?> clazz) {
        manager = manager.isOpen() ? manager : HibernateUtil.getEntityManager();
        return manager.createNativeQuery(query, clazz);
    }

    protected final void refreshObject(T object) {
        manager = manager.isOpen() ? manager : HibernateUtil.getEntityManager();
        manager.refresh(object);
    }

    protected final T executeTransaction(Supplier<T> method) {
        startTransaction();
        T result = method.get();
        commitTransaction();
        return result;
    }

    private void startTransaction() {
        if (!manager.isOpen()) {
            manager = HibernateUtil.getEntityManager();
        } else {
            commitTransaction();
        }
        manager.getTransaction().begin();
    }

    private void commitTransaction() {
        if (manager.getTransaction().isActive()) {
            try {
                manager.getTransaction().commit();
            } catch (Exception e) {
                manager.getTransaction().rollback();
            }
        }
    }

    public final void closeSession() {
        if (manager.isOpen()) {
            commitTransaction();
            manager.close();
        }
    }
}
