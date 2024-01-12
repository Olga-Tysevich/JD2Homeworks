package org.example.lesson2.task2reflection.print;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class Main {
    public static void main(String[] args) {
        try {
            Printer printer = new Printer();
            Method m1 = Printer.class.getDeclaredMethod("print");
            m1.setAccessible(true);
            m1.invoke(Printer.class);
            Method m2 = Printer.class.getMethod("print", int.class);
            m2.invoke(printer, 5);
            Method m3 = Printer.class.getMethod("print", int.class, double.class);
            m3.invoke(printer, 1, 2.5);
            Method m4 = Printer.class.getMethod("print", Integer.class, double.class);
            m4.invoke(printer, 2, 6.5);
            Method m5 = Printer.class.getMethod("print", Object.class);
            m5.invoke(printer, new Object());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
