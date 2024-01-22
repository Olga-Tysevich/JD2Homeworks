package org.example.lesson6.utils;

import org.example.lesson6.connection.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePrinter {
    private static final String SCHEMAS_MESSAGE = "Schemas:";
    private static final String CATALOG_MESSAGE = "%d) %s\n";
    private static final String TABLE_MESSAGE = "Table: %s.%s\n";
    private static final String COLUMN_MESSAGE = "Column: name: %s, type: %s, size: %s, default value: %s\n";
    private static final String COLUMN_NAME_KEY = "COLUMN_NAME";
    private static final String COLUMN_TYPE_KEY = "TYPE_NAME";
    private static final String COLUMN_SIZE_KEY = "COLUMN_SIZE";
    private static final String COLUMN_DEFAULT_KEY = "COLUMN_DEF";

    public static void printAllSchemas() {
        try {
            ResultSet resultCatalogs = SQLConnector.getConnection().getMetaData().getCatalogs();
            System.out.println(SCHEMAS_MESSAGE);
            int count = 1;
            while (resultCatalogs.next()) {
                System.out.printf(CATALOG_MESSAGE, count++, resultCatalogs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTableMeta(String databaseName, String tableName) {
        try {
            ResultSet resultSet = SQLConnector.getConnection().getMetaData().getColumns(databaseName, null,
                    tableName, null);

            System.out.printf(TABLE_MESSAGE, databaseName, tableName);
            while (resultSet.next()) {
                System.out.printf(COLUMN_MESSAGE,
                        resultSet.getString(COLUMN_NAME_KEY),
                        resultSet.getString(COLUMN_TYPE_KEY),
                        resultSet.getString(COLUMN_SIZE_KEY),
                        resultSet.getString(COLUMN_DEFAULT_KEY));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
