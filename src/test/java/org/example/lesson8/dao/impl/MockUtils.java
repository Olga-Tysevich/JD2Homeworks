package org.example.lesson8.dao.impl;

import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson8.dao.impl.MockConstants.CREATE_DATABASE_PATTERN;
import static org.example.lesson8.utils.Constants.PARAMETER_INDEX;
import static org.example.lesson8.utils.Constants.PRIMARY_KEY_ERROR;

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

    private static void createQuery(String query) {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static <T> Object getId(T object) {
        List<Field> idFields = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
        if (idFields.size() == 1) {
            try {
                return idFields.get(0).get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException(PRIMARY_KEY_ERROR);
        }
        return null;
    }

}
