package org.example.lesson10b3.dao;

public interface DAO<T, R> {
    T save(T object);

    T update(T object);

    T get(R id);

    void delete(R id);

    void closeSession();
}
