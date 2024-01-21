package org.example.lesson7.dao;

import java.sql.SQLException;

public interface DAO<T> {
    T save(T object) throws SQLException;
    int update(T object) throws SQLException;
    T get(int id) throws SQLException;
    int delete(T object) throws SQLException;
}
