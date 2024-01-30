package org.example.lesson8.dao.impl;

import org.example.lesson8.annotations.Table;
import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.lesson8.dao.impl.MockConstants.CREATE_DATABASE_PATTERN;
import static org.example.lesson8.dao.impl.MockConstants.DROP_DATABASE_PATTERN;
import static org.example.lesson8.utils.Constants.PARAMETER_INDEX;

public class MockUtils {
    private MockUtils() {
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

    public static void createDatabase(String name) {
        createQuery(CREATE_DATABASE_PATTERN.replace(PARAMETER_INDEX, name));
    }

    public static void createTable(String query) {
        createQuery(query);
    }

    public static void dropDatabase(String fullName) {
        createQuery(DROP_DATABASE_PATTERN.replace(PARAMETER_INDEX, fullName));
    }

    private static void createQuery(String query) {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
