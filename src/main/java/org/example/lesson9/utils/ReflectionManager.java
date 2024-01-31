package org.example.lesson9.utils;

import org.example.lesson9.utils.wrappers.ThrowingFunctionWrapper;

import javax.persistence.Id;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public abstract class ReflectionManager {

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

    public static <T> Object getId(T object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .peek(f -> f.setAccessible(true))
                .map(f -> ThrowingFunctionWrapper.apply(q -> f.get(object), IllegalAccessException.class)
                        .apply(object))
                .findFirst()
                .orElse(null);
    }
}
