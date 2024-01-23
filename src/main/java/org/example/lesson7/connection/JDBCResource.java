package org.example.lesson7.connection;

import java.util.ResourceBundle;


public class JDBCResource {
    private static final String DATABASE = "database";

    private static final String URL_KEY = "url";
    private static final String USER_KEY = "user";
    private static final String PASS_KEY = "password";

    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle(DATABASE);

    private static final String URL = getProperty(URL_KEY);
    private static final String USER = getProperty(USER_KEY);
    private static final String PASSWORD = getProperty(PASS_KEY);

    private static String getProperty(String key) {
        return RESOURCE.getString(key);
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
