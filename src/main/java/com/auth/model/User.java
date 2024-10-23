package com.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.util.Util;

public class User {
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;

    // Constructor for signup
    public User(String userName, String password, String email, String phoneNumber, String address) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Method to register (signup) a new user
    public boolean registerUser() throws SQLException {
        String query = "INSERT INTO users (user_name, password, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.userName);
            statement.setString(2, this.password);  // Store hashed password ideally
            statement.setString(3, this.email);
            statement.setString(4, this.phoneNumber);
            statement.setString(5, this.address);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // Method to validate login credentials
    public static boolean validateLogin(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);  // Compare with hashed password ideally

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // If a record is found, login is valid
        }
    }
}
