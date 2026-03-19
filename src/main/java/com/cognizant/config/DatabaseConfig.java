package com.cognizant.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Update these credentials based on your local MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/training_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Enter your MySQL password here

    private static Connection connection = null;

    /**
     * Returns a singleton connection to the database.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the MySQL Driver (Optional in newer JDBC versions but good practice)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                System.out.println("Successfully connected to the database!");
            } catch (ClassNotFoundException e) {
                System.err.println("JDBC Driver not found: " + e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Closes the connection when the application shuts down.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}