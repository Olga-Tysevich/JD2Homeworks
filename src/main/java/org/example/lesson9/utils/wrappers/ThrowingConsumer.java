package org.example.lesson9.utils.wrappers;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T object) throws E;
}
