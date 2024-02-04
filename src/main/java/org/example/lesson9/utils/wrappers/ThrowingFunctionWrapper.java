package org.example.lesson9.utils.wrappers;

import java.util.function.Function;

public abstract class ThrowingFunctionWrapper {

    public static <T, R, E extends Exception> Function<T, R> apply(ThrowingFunction<T, R, E> function, Class<E> exceptionClass) {
        return object -> {
            try {
                return function.apply(object);
            } catch (Exception e) {
                try {
                    exceptionClass.cast(e);
                    e.printStackTrace();
                } catch (ClassCastException castException) {
                    throw new RuntimeException();
                }
            }
            return null;
        };
    }
}
