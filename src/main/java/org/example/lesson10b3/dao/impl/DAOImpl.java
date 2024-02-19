package org.example.lesson10b3.dao.impl;

import org.example.lesson10b3.dao.DAO;
import org.example.lesson10b3.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public abstract class DAOImpl<T, R> implements DAO<T, R> {
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
    public T get(R id) {
        currentMethod = () -> manager.find(getClazz(), id);
        return executeTransaction(currentMethod);
    }

    @Override
    public void delete(R id) {
        currentMethod = () -> {
            T object = manager.find(getClazz(), id);
            if (object != null) {
                manager.remove(object);
            }
            return object;
        };
        executeTransaction(currentMethod);
    }

    @Override
    public void closeSession() {
        if (manager.isOpen()) {
            manager.close();
        }
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

}
