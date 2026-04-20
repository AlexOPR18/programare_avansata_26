package lab7.prob.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab7.prob.model.Actor;

public class ActorDAO {

	public void create(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO actors (name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public Actor findById(int id) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM actors WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Actor actor = new Actor();
                    actor.setID(rs.getInt("id"));
                    actor.setName(rs.getString("name"));
                    return actor;
                }
            }
        } finally {
            con.close();
        }
        return null;
    }

    public Actor findByName(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM actors WHERE name = ?")) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Actor actor = new Actor();
                    actor.setID(rs.getInt("id"));
                    actor.setName(rs.getString("name"));
                    return actor;
                }
            }
        } finally {
            con.close();
        }
        return null;
    }
}

