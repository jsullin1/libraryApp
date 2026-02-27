package dao;

import db.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

    // INSERT A MEMBER
    public void insertMember(String name, String email) throws Exception {

        String sql = "INSERT INTO Member(Name, Email) " +
                "VALUES(?,?)";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, name);
            ps.setString(2, email);

            ps.executeUpdate();
        }
    }

    public void listAllMembers() throws Exception {
        String sql = "SELECT * FROM Member ORDER BY Name ASC";

        try (Connection conn = Db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())
        {

        }
    }
}
