package org.example.lesson6.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private static Connection connection;

    private SQLConnector() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    JDBCResource.getURL(),
                    JDBCResource.getUSER(),
                    JDBCResource.getPASSWORD());
        }
        return connection;
    }

    public static synchronized void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
