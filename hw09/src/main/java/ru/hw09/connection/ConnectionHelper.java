package ru.hw09.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create db connection.
 */
public class ConnectionHelper {
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            String url = "jdbc:h2:mem:~/test?user=sa";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
