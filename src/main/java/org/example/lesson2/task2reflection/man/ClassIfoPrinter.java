package org.example.lesson2.task2reflection.man;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class ClassIfoPrinter {

    public static void printClassInfo(Class<?> objectClass) {
        System.out.println("Class: " + objectClass.getSimpleName() + "\n");
        List<Field> fields = List.of(objectClass.getDeclaredFields());
        fields.forEach(field -> field.setAccessible(true));
        fields.forEach(field -> System.out.println("Field access modifier: " + hasModifier(field.getModifiers()) + ", field type: " + field.getType()
                + ", field name: " + field.getName()));

        System.out.println();
        List<Constructor<?>> constructors = List.of(objectClass.getDeclaredConstructors());
        constructors.forEach(constructor -> constructor.setAccessible(true));
        constructors.forEach(constructor -> System.out.println("Constructor access modifier: " + hasModifier(constructor.getModifiers()) + ", constructor name: "
                + constructor.getName() +", parameters: " + Arrays.toString(constructor.getParameterTypes())));
        System.out.println();

        List<Method> methods = List.of(objectClass.getDeclaredMethods());
        methods.forEach(method -> method.setAccessible(true));
        methods.forEach(method -> System.out.println("Method access modifier: " + hasModifier(method.getModifiers()) + ", method name: " + method.getName()
                + ", default value: " + method.getDefaultValue() + ", parameters: " + Arrays.toString(method.getParameterTypes())
                + ", return type: " + method.getGenericReturnType()));
    }

    private static String hasModifier(int allModifiers) {
        if ((allModifiers & Modifier.PRIVATE) > 0) {
            return "PRIVATE";
        } else if ((allModifiers & Modifier.PROTECTED) > 0) {
            return "PROTECTED";
        } else if ((allModifiers & Modifier.PUBLIC) > 0) {
            return "PUBLIC";
        } else {
            return "WITHOUT MODIFIER";
        }
    }

}
