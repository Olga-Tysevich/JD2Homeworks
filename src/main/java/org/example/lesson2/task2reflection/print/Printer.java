package org.example.lesson2.task2reflection.print;

import org.example.lesson2.task2reflection.annotation.AcademyInfo;

public class Printer {
    private static void print() {
        System.out.println("Print without parameters");
    }

    public void print(int i) {
        System.out.println("Print with int parameter: " + i);
    }

    public void print(int i, double j) {
        System.out.println("Print with int, double parameters: " + i + ", " + j);
    }

    public void print(Integer i, double j) {
        System.out.println("Print with Integer, double parameters: " + i + ", " + j);
    }

    @AcademyInfo()
    public void print(Object o) {
        System.out.println("Print Object parameter: " + o);
    }

    @AcademyInfo(year = 2023)
    public void print(String text) {
        System.out.println(text);
    }
}
