package org.example.lesson6.connection;

import java.util.ResourceBundle;

public class JDBCResource {
    private static final String DATABASE = "database";

    private static final String URL_KEY = "url";
    private static final String USER_KEY = "user";
    private static final String PASS_KEY = "password";

    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle(DATABASE);

    private static String getValue(String key) {
        return RESOURCE.getString(key);
    }

    private static final String URL = getValue(URL_KEY);
    private  static final String USER = getValue(USER_KEY);
    private static final String PASSWORD = getValue(PASS_KEY);

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
