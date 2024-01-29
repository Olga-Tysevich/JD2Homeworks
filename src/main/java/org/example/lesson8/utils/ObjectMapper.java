package org.example.lesson8.utils;

import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.GeneratedColumn;
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

import static org.example.lesson8.utils.Constants.*;

public class ObjectMapper<T> {
    private final List<Class<? extends Annotation>> allAnnotations = List.of(Table.class, PrimaryKey.class, Column.class, GeneratedColumn.class);

    public String insert(T object) {
        List<Field> fields = getFields(object);
        fields = !fields.isEmpty() ? getUngeneratedColumns(fields) : fields;

        if (!fields.isEmpty()) {
            Map<String, String> columns = getColumns(object, fields);
            return SQLQueryGenerator.createInsert(getDatabaseName(object.getClass()), getTableName(object.getClass()), columns);
        } else {
            throw new IllegalArgumentException(FIELDS_ERROR);
        }
    }

    public String update(T object) {
        List<Field> fields = getFields(object);
        List<Field> keysList = getKeys(object);
        fields = !fields.isEmpty() ? getUngeneratedColumns(fields) : fields;

        if (!fields.isEmpty() && keysList.size() == 1) {
            Map<String, String> columns = getColumns(object, fields);
            Map<String, String> keys = getColumns(object, keysList);

            return SQLQueryGenerator.createUpdate(getDatabaseName(object.getClass()), getTableName(object.getClass()), keys, columns);

        } else if (fields.isEmpty()) {
            throw new IllegalArgumentException(FIELDS_ERROR);
        } else {
            throw new IllegalArgumentException(PRIMARY_KEY_ERROR);
        }
    }

    public String createGet(int id, Class<T> clazz) {
        return createSelectOrDelete(SELECT_QUERY_PATTERN, id, clazz);
    }

    public T get(ResultSet resultSet, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
                List<Field> fields = getFields(instance);
                fields.forEach(ThrowingConsumerWrapper.accept(f -> f.set(instance, resultSet.getObject(f.getName(), f.getType())), Exception.class));
            return instance;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String delete(int id, Class<T> clazz) {
        return createSelectOrDelete(DELETE_QUERY_PATTERN, id, clazz);
    }

    private String createSelectOrDelete(String queryPattern, int id, Class<T> clazz) {
        List<Field> keys = getKeys(getInstance(clazz));

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

    private List<Field> getKeys(T object) {
        return getFields(object).stream()
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .collect(Collectors.toList());
    }

    private Map<String, String> getColumns(T object, List<Field> columns) {
        Map<String, String> result = new LinkedHashMap<>();
        columns.forEach(ThrowingConsumerWrapper.accept(
                c -> result.put(c.getAnnotation(Column.class).name(), "'" + c.get(object) + "'"),
                IllegalAccessException.class));
        return result;
    }

    private List<Field> getFields(T object) {
        Class<?> clazz = object.getClass();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
        if (!fields.isEmpty()) {
            return fields;
        } else {
            throw new IllegalArgumentException(COLUMN_ERROR);
        }
    }

    private List<Field> getUngeneratedColumns(List<Field> fields) {
        List<Class<? extends Annotation>> temp = new ArrayList<>(allAnnotations);
        temp.remove(Column.class);
        return fields.stream()
                .filter(f -> checkAnnotations(f, temp))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
    }

    private boolean checkAnnotations(Field field, List<Class<? extends Annotation>> annotations) {
        return annotations.stream()
                .noneMatch(field::isAnnotationPresent);
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
