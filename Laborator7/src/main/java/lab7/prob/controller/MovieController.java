package lab7.prob.controller;

import lab7.prob.DAO.MovieDAO;
import lab7.prob.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	private final MovieDAO dao = new MovieDAO();
	
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		try {
			List<Movie> movies = dao.getAll();
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            dao.create(movie);
            return new ResponseEntity<>("Filmul a fost adaugat cu succes!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable("id") int id, @RequestBody Movie movie) {
        try {
            dao.update(id, movie);
            return new ResponseEntity<>("Filmul cu ID-ul " + id + " a fost modificat", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la modificare", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<String> updateMovieScore(@PathVariable("id") int id, @RequestParam double score) {
        try {
            dao.updateScore(id, score);
            return new ResponseEntity<>("Scorul a fost actualizat la " + score, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la actualizare scor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int id) {
        try {
            dao.delete(id);
            return new ResponseEntity<>("Filmul cu ID-ul " + id + " a fost sters.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la stergere", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
