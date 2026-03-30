package jdbc_app;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Movie {
	private int id;
	private String title;
	private LocalDate releaseDate;
	private int duration;
	private double score;
	private int genreId;
	private List<Actor> actors = new ArrayList<>();
	
	
public int getID() {
	return id;
}

public void setID(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public LocalDate getReleaseDate() {
	return releaseDate;
}

public void setReleaseDate(LocalDate releaseDate) {
	this.releaseDate = releaseDate;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public double getScore() {
	return score;
}

public void setScore(double score) {
	this.score = score;
}
}
