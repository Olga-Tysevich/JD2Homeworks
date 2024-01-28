package org.example.lesson8.utils;

import java.util.Random;

public class Constants {
    //utils.ObjectMapper
    public static final String TABLE_ANNOTATION_ERROR = "Table annotation is missing";
    public static final String FIELDS_ERROR = "Object has no fields";
    public static final String COLUMN_ERROR = "Object has no fields marked as column";
    public static final String PRIMARY_KEY_ERROR = "Object contains more than one primary key or the key is invalid";
    //utils.generators.QueryGenerator
    public static final String EQUALS_SIGN = " = ";
    public static final String POINT_SIGN = ".";
    public static final String QUOTE_SIGN = "'";
    public static final String PARAMETER_INDEX = "?";
    public static final String PARAMETER_PATTERN = "\\?";
    public static final String INSERT_QUERY_PATTERN = "insert into ?(?) values (?)";
    public static final String UPDATE_QUERY_PATTERN = "update ? set ? where ?";
    public static final String SELECT_QUERY_PATTERN = "select * from ? where ?";
    public static final String DELETE_QUERY_PATTERN = "delete from ? where ?";
    //utils.TableManager
    public static final String CREATE_DATABASE_PATTERN = "create database ?";
    public static final String DROP_DATABASE_PATTERN = "drop database ?";

    //dao.DAOImpl
    public static final String PERSON_IS_NULL = "Persons should not be null";
    //dao.PersonDAOImpl
    public static String GET_BY_PASSPORT_PATTERN = "select * from people.person where passport = ?";
    //dao.HouseDAOImpl
    public static String GET_ALL_HOUSES_PATTERN = "select * from city.houses";
    //dao.DoorDAOImpl
    public static String GET_DOORS_BY_SIZE_PATTERN = "select * from doors_data.doors where size between ? and ?";

    //PersonApp
    public static final String PEOPLE_DATABASE = "people";
    public static final String CREATE_TABLE_PERSON = "create table people.person "
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "age INT, "
            + "salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE, "
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    public static final String PERSONS_IN_FILE_PATH = "src\\main\\resources\\persons.json";
    public static final String PERSONS_OUT_FILE_PATH = "src\\main\\resources\\personsWithId.json";

    //HouseApp
    public static final String CITY_DATABASE = "city";
    public static final String CREATE_TABLE_HOUSES = "create table city.houses"
            + "(id int primary key auto_increment, "
            + "size DECIMAL, "
            + "color VARCHAR(50), "
            + "room_count INT)";
    public static final String HOUSES_IN_FILE_PATH = "src\\main\\resources\\houses.json";
    public static final String HOUSES_OUT_FILE_PATH = "src\\main\\resources\\housesWithId.json";

    //DoorApp
    public static final String DOOR_DATABASE = "doors_data";
    public static final String CREATE_TABLE_DOORS = "create table doors_data.doors"
            + "(id int primary key auto_increment, "
            + "size DECIMAL(32, 2), "
            + "type varchar(50))";
    public static final String DOORS_IN_FILE_PATH = "src\\main\\resources\\doors.json";
    public static final String DOORS_OUT_FILE_PATH = "src\\main\\resources\\doorsWithId.json";

    //All
    public static final Random RANDOM = new Random();

    private Constants() {
    }
}
