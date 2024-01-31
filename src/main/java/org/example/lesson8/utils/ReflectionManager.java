package org.example.lesson8.utils;

import org.example.lesson8.annotations.Column;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.annotations.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson8.utils.Constants.TABLE_ANNOTATION_ERROR;

public abstract class ReflectionManager {
    public static <T> List<Field> getAllFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
    }

    public static <T> List<Field> getAllKeys(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && f.isAnnotationPresent(PrimaryKey.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
    }

    public static <T> List<Field> getNonGeneratedColumns(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(PrimaryKey.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
    }


    public static String getDatabaseName(Class<?> clazz) {
        try {
            return clazz.getAnnotation(Table.class).databaseName();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(TABLE_ANNOTATION_ERROR);
        }
    }

    public static String getTableName(Class<?> clazz) {
        try {
            return clazz.getAnnotation(Table.class).tableName();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(TABLE_ANNOTATION_ERROR);
        }
    }

    public static String getColumnName(Field field) {
        return field.getAnnotation(Column.class).name();
    }

    public static <T>  T getInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
