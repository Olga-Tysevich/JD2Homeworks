package org.example.lesson8.dao.impl;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class MockConstants {
    public static final List<Integer> PERSONS_AGE = List.of(10, 20, 30, 40, 50);
    public static final List<Double> PERSONS_SALARY = List.of(100.0, 200.0, 300.0, 400.0, 500.0);
    public static final List<String> PERSONS_PASSPORT = List.of("MP12345678", "MP23145687", "MP87546395", "MP63546936", "MP26983652");
    public static final List<String> PERSONS_ADDRESS = List.of("address1", "address2", "address3", "address4", "address5");
    public static final List<Date> PERSONS_BIRTHDAY = List.of(Date.valueOf("2014-01-11"), Date.valueOf("2001-12-15"), Date.valueOf("1989-04-07"),
            Date.valueOf("2005-11-28"), Date.valueOf("1999-01-05"));
    public static final List<Time> PERSONS_LUNCH = List.of(Time.valueOf("11:00:00"), Time.valueOf("13:30:00"), Time.valueOf("14:00:00"),
            Time.valueOf("13:45:00"), Time.valueOf("12:20:00"));
    public static final List<String> PERSONS_LETTER = List.of("letter1", "letter2", "letter3", "letter4", "letter5");

    public static final double FROM_SIZE = 900;
    public static final double TO_SIZE = 1300;
    public static final List<Double> DOORS_SIZE = List.of(210.5, 1300.0, 950.0, 207.1, 1200.0);
    public static final List<String> DOORS_TYPE = List.of("одностворчатые", "двустворчатые", "полуторные", "another", "test");

    public static final List<Double> HOUSES_SIZE = List.of(150D, 62D, 85D, 120D, 54D);
    public static final List<String> HOUSES_COLOR = List.of("белый", "черный", "зеленый", "черно-белый", "красный");
    public static final List<Integer> HOUSES_ROOM = List.of(5, 3, 4, 7, 3);

    private MockConstants() {
    }
}
