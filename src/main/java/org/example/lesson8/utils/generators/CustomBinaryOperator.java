package org.example.lesson8.utils.generators;

import java.util.Map;

public interface CustomBinaryOperator<E extends Map<T, T>, T> {
    T apply(E e, T t);
}
