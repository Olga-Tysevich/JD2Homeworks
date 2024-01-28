package org.example.lesson8.utils;

import org.example.lesson8.connection.SQLConnection;

import java.sql.SQLException;
import java.sql.Statement;

import static org.example.lesson8.utils.Constants.*;

public class TableManager {


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
