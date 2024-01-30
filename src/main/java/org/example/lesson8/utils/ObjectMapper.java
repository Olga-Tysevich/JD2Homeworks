package org.example.lesson8.utils;

import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.annotations.Table;
import org.example.lesson8.utils.generators.SQLQueryGenerator;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.lesson8.utils.Constants.*;

public class ObjectMapper<T> {

    public String generateInsert(T object) {
        Map<String, String> columns = getFieldsForQuery(object, getNonGeneratedColumns(getAllFields(object)));
        List<Field> fields = getNonGeneratedColumns(getAllFields(object));

        if (fields.isEmpty()) {
            throw new IllegalArgumentException(FIELDS_ERROR);
        }
        return SQLQueryGenerator.createInsert(getDatabaseName(object.getClass()), getTableName(object.getClass()), columns);
    }

    public String generateUpdate(T object) {
        Map<String, String> columns = getFieldsForQuery(object, getNonGeneratedColumns(getAllFields(object)));
        Map<String, String> keys = getFieldsForQuery(object, getAllKeys(object));

        if (columns.isEmpty() || keys.size() != 1) {
            throw new IllegalArgumentException(columns.isEmpty() ? FIELDS_ERROR : PRIMARY_KEY_ERROR);
        }
        return SQLQueryGenerator.createUpdate(getDatabaseName(object.getClass()), getTableName(object.getClass()), keys, columns);
    }

    public String generateGet(int id, Class<T> clazz) {
        return createSelectOrDelete(SELECT_QUERY_PATTERN, id, clazz);
    }

    public T getObject(ResultSet resultSet, Class<T> clazz) {
        T instance = getInstance(clazz);
        if (instance != null) {
            List<Field> fields = getAllFields(instance);
            fields.forEach(
                    ThrowingConsumerWrapper.accept(f ->
                            f.set(instance, resultSet.getObject(getColumnName(f), f.getType())),
                            Exception.class));
        }
        return instance;
    }

    public String generateDelete(int id, Class<T> clazz) {
        return createSelectOrDelete(DELETE_QUERY_PATTERN, id, clazz);
    }

    private String getColumnName(Field field) {
        return field.getAnnotation(Column.class).name();
    }

    private String createSelectOrDelete(String queryPattern, int id, Class<T> clazz) {
        List<Field> keys = getAllKeys(getInstance(clazz));

        if (keys.size() == 1) {
            String databaseName = getDatabaseName(clazz);
            String tableName = getTableName(clazz);
            String primaryKeyName = keys.get(0).getAnnotation(Column.class).name();
            return SQLQueryGenerator.createSelectOrDelete(databaseName, queryPattern, tableName, primaryKeyName, String.valueOf(id));
        } else {
            throw new IllegalArgumentException(PRIMARY_KEY_ERROR);
        }
    }

    private T getInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Field> getAllFields(T object) {
        Class<?> clazz = object.getClass();
        return filterByAnnotation(Arrays.stream(clazz.getDeclaredFields()), Column.class, true);
    }

    private List<Field> getAllKeys(T object) {
        return filterByAnnotation(getAllFields(object).stream(), PrimaryKey.class, true);
    }

    private List<Field> getNonGeneratedColumns(List<Field> fields) {
        return filterByAnnotation(fields.stream(), PrimaryKey.class,false);
    }

    private List<Field> filterByAnnotation(Stream<Field> stream, Class<? extends Annotation> annotation, boolean isPresent) {
        return stream.filter(f -> isPresent == f.isAnnotationPresent(annotation))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
    }

    private Map<String, String> getFieldsForQuery(T object, List<Field> columns) {
        Map<String, String> result = new LinkedHashMap<>();
        columns.forEach(ThrowingConsumerWrapper.accept(
                c -> result.put(c.getAnnotation(Column.class).name(), "'" + c.get(object) + "'"),
                IllegalAccessException.class));
        return result;
    }

    private String getDatabaseName(Class<?> clazz) {
        try {
            return clazz.getAnnotation(Table.class).databaseName();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(TABLE_ANNOTATION_ERROR);
        }
    }

    private String getTableName(Class<?> clazz) {
        try {
            return clazz.getAnnotation(Table.class).tableName();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(TABLE_ANNOTATION_ERROR);
        }
    }

}
