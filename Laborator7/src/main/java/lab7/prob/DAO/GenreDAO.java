package lab7.prob.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab7.prob.model.Genre;

public class GenreDAO {
	public void create(String name) throws SQLException {
		Connection con = Database.getInstance().getConnection();
		
		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO genres (name) VALUES (?)")) {
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} finally {
			con.close();
		}
	}
	
	public Genre findById(int id) throws SQLException {
		Connection con = Database.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement("SELECT * FROM genres WHERE id = ?")) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					Genre genre = new Genre();
					genre.setID(rs.getInt("id"));
					genre.setName(rs.getString("name"));
					return genre;
				}
			}
		} finally {
			con.close();
		}
		return null;
	}
	
	public Genre findByName(String name) throws SQLException {
		Connection con = Database.getInstance().getConnection();
		
		try(PreparedStatement pstmt = con.prepareStatement("SELECT * FROM genres WHERE name = ?")) {
			pstmt.setString(1, name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					Genre genre = new Genre();
					genre.setID(rs.getInt("id"));
					genre.setName(rs.getString("name"));
					return genre;
				}
			}
		} finally {
			con.close();		
		}
		return null;
	}
}
