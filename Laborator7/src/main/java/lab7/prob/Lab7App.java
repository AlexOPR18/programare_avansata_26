package lab7.prob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lab7.prob.client.MovieClient;

@SpringBootApplication
public class Lab7App {
	
	public static void main(String[] args) {
		SpringApplication.run(Lab7App.class, args);
		System.out.println("Gata. Serverul ruleaza");
		
		MovieClient client = new MovieClient();
		client.testAddMovie();
		client.testGetMovies();
	}
}
