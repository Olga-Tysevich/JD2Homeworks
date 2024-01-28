package org.example.lesson8.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static Connection instance;

    private SQLConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(JDBCResource.getURL(), JDBCResource.getUSER(), JDBCResource.getPASSWORD());
        }
        return instance;
    }

    public static void closeConnection() throws SQLException {
        instance.close();
        instance = null;
    }
}
