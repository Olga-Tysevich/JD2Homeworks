package org.example.lesson8.dao.impl;

import org.example.lesson8.dto.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.lesson8.dao.impl.MockConstants.*;

public class MockUtils {

    public static Person getPerson() {
        return buildPerson(AGE, SALARY, PASSPORT, ADDRESS);
    }

    public static List<Person> getPersons() {
        return IntStream.rangeClosed(1, 5).mapToObj(i -> MockUtils.buildPerson(i * 10, SALARY, PASSPORT,
                ADDRESS))
                .collect(Collectors.toList());
    }

    private static Person buildPerson(int age, double salary, String passport, String address) {
        return Person.builder()
                .age(age)
                .salary(salary)
                .passport(passport)
                .address(address)
                .dateOfBirthday(BIRTHDAY)
                .timeToLunch(LUNCH)
                .build();
    }
}
