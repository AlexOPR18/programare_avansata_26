package lab7.prob.client;

import org.springframework.web.client.RestTemplate;

import lab7.prob.model.Movie;

public class MovieClient {

	public void testGetMovies() {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "http://localhost:8083/api/movies";
        
        System.out.println("Trimite cerere GET catre server");
        
        String response = restTemplate.getForObject(url, String.class);
        
        System.out.println("Răspuns primit de la server:");
        System.out.println(response);
    }
	
	public void testAddMovie() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/movies";
    
        Movie newMovie = new Movie();
        newMovie.setTitle("Two Distant Strangers");
        newMovie.setDuration(32);
        newMovie.setScore(6.8);
        newMovie.setReleaseDate(java.time.LocalDate.of(2020, 11, 20));
        
        System.out.println("Trimit cerere POST catre server");
        
        String response = restTemplate.postForObject(url, newMovie, String.class);
        
        System.out.println("Răspuns primit după adăugare:");
        System.out.println(response);
    }
}
