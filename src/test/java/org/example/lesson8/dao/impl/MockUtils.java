package org.example.lesson8.dao.impl;

import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.dto.PersonDTO;

import java.sql.Date;
import java.sql.Time;

public class MockUtils {
    private MockUtils() {
    }

    public static PersonDTO buildPerson(int age, double salary, String passport, String address, Date birthday,
                                        Time lunch, String letter) {
        return PersonDTO.builder()
                .age(age)
                .salary(salary)
                .passport(passport)
                .address(address)
                .dateOfBirthday(birthday)
                .timeToLunch(lunch)
                .letter(letter)
                .build();
    }

    public static DoorDTO buildDoor(double size, String type) {
        return DoorDTO.builder()
                .size(size)
                .type(type)
                .build();
    }

    public static HouseDTO buildHouse(double size, String color, int rooms) {
        return HouseDTO.builder()
                .size(size)
                .color(color)
                .roomCount(rooms)
                .build();
    }

}
