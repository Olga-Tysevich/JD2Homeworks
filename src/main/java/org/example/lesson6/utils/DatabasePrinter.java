package org.example.lesson6.utils;

import org.example.lesson6.connection.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePrinter {

    public static void printAllSchemas() {
        try {
            ResultSet resultCatalogs = SQLConnector.getConnection().getMetaData().getCatalogs();
            System.out.println("Schemas:");
            int count = 1;
            while (resultCatalogs.next()) {
                System.out.println(count++ + ") " + resultCatalogs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTableMeta(String databaseName, String tableName) {
        try {
            ResultSet resultSet = SQLConnector.getConnection().getMetaData().getColumns(databaseName, null,
                    tableName, null);

            System.out.println("Table: " + databaseName + "." + tableName);
            while (resultSet.next()) {
                System.out.println("Column: name: " + resultSet.getString("COLUMN_NAME")
                        + ", type: " + resultSet.getString("TYPE_NAME")
                        + ", size: " + resultSet.getString("COLUMN_SIZE")
                        + ", default value: " + resultSet.getString("COLUMN_DEF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
