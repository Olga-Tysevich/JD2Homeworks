package org.example.lesson8.utils;

import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class ResultSetHandler {
    public static <T> T getObject(ResultSet resultSet, Class<T> clazz) throws SQLException {
        T result = ReflectionManager.getInstance(clazz);
        if (resultSet.next()) {
            if (result != null) {
                List<Field> fields = ReflectionManager.getAllFields(clazz);
                fields.forEach(
                        ThrowingConsumerWrapper.accept(f ->
                                        f.set(result, resultSet.getObject(ReflectionManager.getColumnName(f), f.getType())),
                                Exception.class));
            }
        }
        return result;
    }
}
