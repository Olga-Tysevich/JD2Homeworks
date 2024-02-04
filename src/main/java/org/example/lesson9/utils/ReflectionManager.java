package org.example.lesson9.utils;

import org.example.lesson9.utils.wrappers.ThrowingFunctionWrapper;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class ReflectionManager {

    public static <T> Object getId(T object) {
        Field[] fields = object.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Id.class))
                .peek(f -> f.setAccessible(true))
                .map(f -> ThrowingFunctionWrapper.apply(q -> f.get(object), IllegalAccessException.class)
                        .apply(object))
                .findFirst()
                .orElse(null);
    }
}
