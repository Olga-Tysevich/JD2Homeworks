package org.example.lesson9.dao;

import java.io.Serializable;

public interface DAO<T extends Serializable> {
    T save(T object, Class<T> clazz);

    T update(T object);

    T get(int id, Class<T> clazz);

    void delete(int id, Class<T> clazz);
}
