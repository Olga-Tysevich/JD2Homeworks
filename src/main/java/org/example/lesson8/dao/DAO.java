package org.example.lesson8.dao;

import java.sql.SQLException;

public interface DAO<T> {
    T save(T object, Class<T> clazz) throws SQLException;

    int update(T object) throws SQLException;

    T get(int id, Class<T> clazz) throws SQLException;

    int delete(int id, Class<T> clazz) throws SQLException;
}
