package org.example.lesson7.utils;

import java.util.function.Consumer;

public class ThrowingConsumerWrapper {

    public static <T, E extends Exception> Consumer<T> accept(ThrowingConsumer<T, E> consumer, Class<E> exceptionClass) {
        return object -> {
            try {
                consumer.accept(object);
            } catch (Exception e) {
                try {
                    E exception = exceptionClass.cast(e);
                    exception.printStackTrace();
                } catch (ClassCastException castException) {
                    throw new RuntimeException();
                }
            }
        };
    }
}
