package dao;

import db.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CheckoutDao {

    public void checkoutBook(int bookId, int memberId, int employeeId) throws Exception {

        String sql = """
                INSERT INTO Checkout (BookID, MemberID, EmployeeID, CheckoutDate)
                VALUES (?, ?, ?, CURDATE())
                """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setInt(3, employeeId);

            ps.executeUpdate();
        }
    }

    public void returnBook(int checkoutId) throws Exception {

        String sql = """
                UPDATE Checkout
                SET ReturnDate = CURDATE()
                WHERE CheckoutID = ?
                """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, checkoutId);
            ps.executeUpdate();
        }
    }
}