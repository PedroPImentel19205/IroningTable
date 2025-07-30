package DB;

import java.sql.*;

public class DB {

    private static Connection conn = null; // Static variable for the connection

    // --- Método para obter a conexão ---
    public static Connection getConnection() {
        if (conn == null) { // If the connection has not yet been established
            try {
                // It's good practice to explicitly load the driver for older JDBC versions.
                // For JDBC 4.0+ (Java 6+), this is generally not necessary, but it doesn't hurt.
                // Class.forName("com.mysql.cj.jdbc.Driver"); // Example for MySQL Driver 8.x+

                // Use the constants from your ConnectionConfig
                conn = DriverManager.getConnection(
                        ConnectionConfig.URL,
                        ConnectionConfig.USER,
                        ConnectionConfig.PASSWORD
                );
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (SQLException e) {
                // Throw a custom exception or handle the connection error
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    // --- Method to close the connection ---
    public static void closeConnection() { // We changed it to not receive Connection as a parameter
        if (conn != null) { // Only tries to close if the connection exists
            try {
                conn.close();
                conn = null; // Set the connection to null after closing
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    // --- Method to close Statement ---
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    // --- Method to close ResultSet ---
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

}
