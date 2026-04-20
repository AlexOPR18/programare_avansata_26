package lab7.prob.DAO;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import lab7.prob.model.Movie;

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
    
    public List<Movie> getAll() throws SQLException {
        Connection con = Database.getInstance().getConnection();
        List<Movie> movies = new ArrayList<>();
        
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM movies");
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setID(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                
                if(rs.getDate("release_date") != null) {
                    movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
                }
                
                movie.setDuration(rs.getInt("duration"));
                movie.setScore(rs.getDouble("score")); 
                movies.add(movie);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return movies;
    }
    public void create(Movie movie) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO movies (title, release_date, duration, score) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setDate(2, movie.getReleaseDate() != null ? java.sql.Date.valueOf(movie.getReleaseDate()) : null);
            pstmt.setInt(3, movie.getDuration());
            pstmt.setDouble(4, movie.getScore());
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public void update(int id, Movie movie) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE movies SET title = ?, duration = ?, score = ? WHERE id = ?")) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getDuration());
            pstmt.setDouble(3, movie.getScore());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public void updateScore(int id, double newScore) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE movies SET score = ? WHERE id = ?")) {
            pstmt.setDouble(1, newScore);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }

    public void delete(int id) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("DELETE FROM movies WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            con.close();
        }
    }
}