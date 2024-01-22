package org.example.lesson7.utils;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T object) throws E;
}
