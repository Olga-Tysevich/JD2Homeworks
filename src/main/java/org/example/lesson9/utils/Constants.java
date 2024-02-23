package org.example.lesson9.utils;

public abstract class Constants {

    public static final String PEOPLE_IN_FILE_PATH = "src\\main\\resources\\persons.json";
    public static final String ADDRESSES_IN_FILE_PATH = "src\\main\\resources\\addresses.json";
    public static final String INCREASE_AGE = "update people_join set age = age + :increment where id = :id";
    public static final String CHANGE_HOUSE_NUMBER = "update addresses_join set house = house + :increment where id = :id";
    public static final String INCREMENT = "increment";
    public static final String ID = "id";

    private Constants() {
    }
}