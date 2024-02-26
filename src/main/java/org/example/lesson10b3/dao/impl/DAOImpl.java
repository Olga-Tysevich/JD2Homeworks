package org.example.lesson10b3.dao.impl;

import org.example.lesson10b3.dao.DAO;
import org.example.lesson10b3.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

public abstract class DAOImpl<T, R> implements DAO<T, R> {
    private EntityManager manager = HibernateUtil.getEntityManager();

    protected abstract Class<T> getClazz();

    @Override
    public T save(T object) {
        checkManager();
        executeTransaction(manager::persist, object);
        return object;
    }

    @Override
    public T update(T object) {
        checkManager();
        executeTransaction(manager::merge, object);
        return object;
    }

    @Override
    public T get(R id) {
        checkManager();
        return manager.find(getClazz(), id);
    }

    @Override
    public void delete(R id) {
        checkManager();
        T objectForDelete = manager.find(getClazz(), id);
        if (objectForDelete != null) {
            executeTransaction(manager::remove, objectForDelete);
        }
    }

    protected final void executeTransaction(Consumer<T> method, T object) {
        manager.getTransaction().begin();
        method.accept(object);
        commitTransaction();
    }

    protected EntityManager getManager() {
        checkManager();
        return manager;
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

    private void checkManager() {
        if (!manager.isOpen()) {
            manager = HibernateUtil.getEntityManager();
        } else if (manager.getTransaction().isActive()) {
            commitTransaction();
        }
    }

    public final void closeSession() {
        if (manager.isOpen()) {
            commitTransaction();
            manager.close();
        }
    }
}