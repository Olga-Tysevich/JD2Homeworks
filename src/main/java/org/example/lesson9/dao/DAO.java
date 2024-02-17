package org.example.lesson9.dao;

public interface DAO<T> {
    T save(T object);

    T update(T object);

    T get(int id);

    void delete(int id);

    void closeSession();

}
