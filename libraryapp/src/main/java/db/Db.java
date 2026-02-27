package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306/deliverable_4";
    private static final String USER = "root";  //change to your username
    private static final String PASSWORD = "1234qwer!@#$QWER";  //change to your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}