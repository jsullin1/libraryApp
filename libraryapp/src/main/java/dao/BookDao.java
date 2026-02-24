package dao;

import db.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {

    public void insertBook(String title, int year, int copies) throws Exception {

        String sql = """
                INSERT INTO Book (ISBN, Title, PublicationYear, TotalCopies)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, year);
            ps.setInt(3, copies);

            ps.executeUpdate();
        }
    }

    public void listAllBooks() throws Exception {

        String sql = "SELECT * FROM Book ORDER BY BookID";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.printf(
                        "%d | %s | %s | Year: %s | Copies: %d%n",
                        rs.getInt("BookID"),
                        rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getObject("PublicationYear"),  // handles NULL safely
                        rs.getInt("TotalCopies")
                );
            }
        }
    }

    public void deleteBook(int id) throws Exception {

        String sql = "DELETE FROM Book WHERE BookID = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void updateBookCopies(int bookId, int newCopies) throws Exception {

        String sql = """
        UPDATE Book
        SET TotalCopies = ?
        WHERE BookID = ?
        """;

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newCopies);
            ps.setInt(2, bookId);

            ps.executeUpdate();
        }
    }
}