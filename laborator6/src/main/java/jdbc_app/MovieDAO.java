package jdbc_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class MovieDAO {

    public void create(String title, LocalDate releaseDate, int duration, double score, int genreId) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        String sql = "INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setDate(2, Date.valueOf(releaseDate)); 
            pstmt.setInt(3, duration);
            pstmt.setDouble(4, score);
            pstmt.setInt(5, genreId); 
            
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public void addActorToMovie(int movieId, int actorId) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        String sql = "INSERT INTO movie_actors (movie_id, actor_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public Movie findByTitle(String title) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM movies WHERE title = ?")) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new Movie();
                    movie.setID(rs.getInt("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
                    movie.setDuration(rs.getInt("duration"));
                    movie.setScore(rs.getDouble("score")); 
                    return movie;
                }
            }
        } finally {
            con.close();
        }
        return null;
    }
}