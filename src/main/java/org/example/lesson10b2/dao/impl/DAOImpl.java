package org.example.lesson10b2.dao.impl;

import org.example.lesson10b2.dao.DAO;
import org.example.lesson10b2.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public abstract class DAOImpl<T> implements DAO<T> {
    private EntityManager manager = HibernateUtil.getEntityManager();
    private Supplier<T> currentMethod;

    protected abstract Class<T> getObjectClass();

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
        currentMethod = () -> manager.find(getObjectClass(), id);
        return executeTransaction(currentMethod);
    }

    @Override
    public void delete(int id) {
        currentMethod = () -> {
            T objectForDelete = manager.find(getObjectClass(), id);
            if (objectForDelete != null) {
                manager.remove(objectForDelete);
            }
            return objectForDelete;
        };
        executeTransaction(currentMethod);
    }

    protected T executeTransaction(Supplier<T> method) {
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

    @Override
    public void closeSession() {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}
