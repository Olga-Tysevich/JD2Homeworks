package org.example.lesson8.dao.impl;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.utils.ObjectMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.lesson8.utils.Constants.PERSON_IS_NULL;

public class DAOImpl<T> implements DAO<T> {
    private final ObjectMapper<T> mapper = new ObjectMapper<>();

    @Override
    public T save(T object, Class<T> clazz) throws SQLException {
        T result;
        if (object != null) {
            try (PreparedStatement statement = SQLConnection.getConnection()
                    .prepareStatement(mapper.generateInsert(object), Statement.RETURN_GENERATED_KEYS)) {
                int affectedRaw = statement.executeUpdate();
                int id = getId(statement, affectedRaw);
                result = get(id, clazz);
                return result != null ? result : object;
            }
        } else {
            throw new IllegalArgumentException(PERSON_IS_NULL);
        }
    }

    @Override
    public int update(T object) throws SQLException {
        if (object != null) {
            try (PreparedStatement statement = SQLConnection.getConnection()
                    .prepareStatement(mapper.generateUpdate(object))) {
                return statement.executeUpdate();
            }
        } else {
            throw new IllegalArgumentException(PERSON_IS_NULL);
        }
    }

    @Override
    public T get(int id, Class<T> clazz) throws SQLException {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(mapper.generateGet(id, clazz));
            return resultSet.next() ? mapper.getObject(resultSet, clazz) : null;
        }
    }

    @Override
    public int delete(int id, Class<T> clazz) throws SQLException {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            return statement.executeUpdate(mapper.generateDelete(id, clazz));
        }
    }

    private int getId(PreparedStatement statement, int affectedRaw) throws SQLException {
        int id = 0;
        if (affectedRaw > 0) {
            try (ResultSet idResult = statement.getGeneratedKeys()) {
                if (idResult.next()) {
                    id = idResult.getInt(1);
                }
            }
        }
        return id;
    }

    public ObjectMapper<T> getMapper() {
        return mapper;
    }
}
