package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
    private static final String URL = "jdbc:postgresql://localhost:5432/librarydb_team15";
    private static final String USER = "postgres";  //change to your username
    private static final String PASSWORD = "Getmoney2";  //change to your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}