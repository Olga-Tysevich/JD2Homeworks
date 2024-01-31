package org.example.lesson8.utils.wrappers;

@FunctionalInterface
public interface ThrowingFunction <T, R, E extends Exception>{
    R apply(T object) throws E;
}
