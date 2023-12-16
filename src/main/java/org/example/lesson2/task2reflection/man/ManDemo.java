package org.example.lesson2.task2reflection.man;


import org.example.lesson2.task1scientists.models.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/*Создать класс Man c произвольным набором полей и методов (не менее 3) Создать метод,
который распечатает информацию о классе с помощью рефлексии. Вызвать метод с помощью рефлексии из основной программы.*/
public class ManDemo {
    public static void main(String[] args) {
        Man man = new Man("New man", 25, 175, 65);
        ClassIfoPrinter.printClassInfo(Man.class);
        ClassIfoPrinter.printClassInfo(Factory.class);

        System.out.println(createMan(Man.class));

        changeMan(man);
        System.out.println(man);
    }

    public static Man createMan(Class<Man> manClass) {
        try {
            Constructor<Man> constructor = manClass.getConstructor(String.class, int.class, double.class, double.class);
            return constructor.newInstance("Some man", new Random().nextInt(51),
                    new Random().nextInt(2000) * 0.1, new Random().nextInt(800) * 0.1);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void changeMan(Man man) {
        Class<?> manClass = man.getClass();
        try {
            Method setName = manClass.getDeclaredMethod("setName", String.class);
            setName.invoke(man, "New name");
            Field age = manClass.getDeclaredField("age");
            age.setAccessible(true);
            age.set(man, 31);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
