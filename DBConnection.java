package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for database connection
 * This class manages the MySQL database connection
 */
public class DBConnection {
    
    // Database configuration constants
    private static final String URL = "jdbc:mysql://localhost:3306/event_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ronak@123"; // Change this!
    
    // Static block to load the JDBC driver
    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL Driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Get a connection to the database
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Database connected successfully!");
        return conn;
    }
    
    /**
     * Close the database connection safely
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}