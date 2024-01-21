package org.example.lesson7.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static Connection instance;

    private SQLConnection() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(JDBCResource.getURL(), JDBCResource.getUSER(), JDBCResource.getPASSWORD());
        }
        return instance;
    }

    public static synchronized void closeConnection() throws SQLException {
        instance.close();
        instance = null;
    }
}
