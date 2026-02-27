package dao;

import db.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckoutDao {

    public void checkoutBook(int bookId, int memberId, int employeeId, String dueDate) throws Exception {

        String sql = """
            INSERT INTO Checkout (BookID, MemberID, EmployeeID, CheckoutDate, DueDate)
            VALUES (?, ?, ?, CURRENT_DATE, ?)
            """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setInt(3, employeeId);
            ps.setDate(4, java.sql.Date.valueOf(dueDate));

            ps.executeUpdate();
        }
    }

    public void returnBook(int checkoutId) throws Exception {

        String sql = """
            UPDATE Checkout
            SET ReturnDate = CURRENT_DATE
            WHERE CheckoutID = ?
            """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, checkoutId);
            ps.executeUpdate();
        }
    }

    public void listOverdue() throws Exception {
        String sql = """
        SELECT c.CheckoutID, m.Name, b.Title, c.DueDate
        FROM Checkout c
        JOIN Member m ON m.MemberID = c.MemberID
        JOIN Book b ON b.BookID = c.BookID
        WHERE c.ReturnDate IS NULL AND c.DueDate < CURRENT_DATE()
        ORDER BY c.DueDate
        """;
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.printf("%d | %s | %s | Due: %s%n",
                        rs.getInt("CheckoutID"),
                        rs.getString("Name"),
                        rs.getString("Title"),
                        rs.getDate("DueDate"));
            }
        }
    }
}