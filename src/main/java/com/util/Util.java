package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/Ras";  // Replace with your DB details
        String username = "root";  // Replace with your DB username
        String password = "Chetan12@";  // Replace with your DB password

        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
