package org.example.lesson2.task2reflection.annotation;

import org.example.lesson2.task2reflection.print.Printer;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class AnnotationDemo {
    public static void main(String[] args) {
        checkAnnotation(Printer.class);
    }

    public static void checkAnnotation(Class<?> objectClass) {
        List<Method> methods = List.of(objectClass.getDeclaredMethods());
        methods.forEach(m -> m.setAccessible(true));
        for (Method m : methods) {
            if (m.isAnnotationPresent(AcademyInfo.class)) {
                System.out.println("Annotation @AcademyInfo is present in the method: " + m.getName()
                        + " with parameters: " + Arrays.toString(m.getParameterTypes()));
                AcademyInfo a = m.getAnnotation(AcademyInfo.class);
                System.out.println("Year: " + a.year());
            }
        }

    }
}
