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
import java.sql.Date;
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
        T instance = getInstance(clazz);
        if (instance != null) {
            List<Field> fields = getFields(instance);
            fields.forEach(ThrowingConsumerWrapper.accept(f -> setFieldValue(resultSet, f, instance), Exception.class));
        }
        return instance;
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
                c -> result.put(c.getAnnotation(Column.class).name(), getValue(c, object)),
                IllegalAccessException.class));
        return result;
    }

    private String getValue(Field field, T object) {
        try {
            return isInteger(field) || isDouble(field) ?
                    field.get(object).toString() :
                    QUOTE_SIGN + field.get(object) + QUOTE_SIGN;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void setFieldValue(ResultSet resultSet, Field field, T object) {
        try {
            if (isDouble(field)) {
                field.set(object, resultSet.getDouble(field.getAnnotation(Column.class).name()));
            } else if (isInteger(field)) {
                field.set(object, resultSet.getInt(field.getAnnotation(Column.class).name()));
            } else if (isDate(field)) {
                field.set(object, resultSet.getDate(field.getAnnotation(Column.class).name()));
            } else if (isTime(field)) {
                field.set(object, resultSet.getTime(field.getAnnotation(Column.class).name()));
            } else if (isTimestamp(field)) {
                field.set(object, resultSet.getTimestamp(field.getAnnotation(Column.class).name()));
            } else if (isString(field)) {
                field.set(object, resultSet.getString(field.getAnnotation(Column.class).name()));
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean isDate(Field field) {
        return field.getType().equals(Date.class);
    }

    private boolean isTime(Field field) {
        return field.getType().equals(Time.class);
    }

    private boolean isString(Field field) {
        return field.getType().equals(String.class) || field.getType().equals(char.class);
    }

    private boolean isTimestamp(Field field) {
        return field.getType().equals(Timestamp.class);
    }

    private boolean isDouble(Field field) {
        return field.getType().equals(double.class) || field.getType().equals(Double.class);
    }

    private boolean isInteger(Field field) {
        return field.getType().equals(int.class) || field.getType().equals(Integer.class);
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
        List<Boolean> temp = annotations.stream()
                .map(field::isAnnotationPresent)
                .collect(Collectors.toList());
        return !temp.contains(true);
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
