package org.example.lesson8.dao;

import java.io.Serializable;

public interface DAO<T extends Serializable> {
    T save(T object);
    T get(int id, Class<T> clazz);
    T update(T object);
    void delete(int id, Class<T> clazz);
}
