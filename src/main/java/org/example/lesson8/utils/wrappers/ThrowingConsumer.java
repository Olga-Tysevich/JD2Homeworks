package org.example.lesson8.utils.wrappers;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T object) throws E;
}
