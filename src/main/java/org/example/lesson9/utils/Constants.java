package org.example.lesson9.utils;

import java.util.Random;

public abstract class Constants {

    public static final String PEOPLE_IN_FILE_PATH = "src\\main\\resources\\persons.json";
    public static final String ADDRESSES_IN_FILE_PATH = "src\\main\\resources\\addresses.json";
    public static final String INCREASE_AGE = "update people_join_many_to_many set age = age + :increment where id = :id";
    public static final String CHANGE_HOUSE_NUMBER = "update addresses_join_many_to_many set house = house + :increment where id = :id";
    public static final String INCREMENT = "increment";
    public static final String ID = "id";
    public static final Random RANDOM = new Random();

    private Constants() {
    }
}
