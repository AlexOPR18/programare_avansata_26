public class Article implements Resource {
    private String id;
    private String title;
    private String author;
    private String location;
    private int year;
    
    public Article(String id, String title, String location, int year, String author) {
    	this.id = id;
    	this.title = title;
    	this.author = author;
    	this.location = location;
    	this.year = year;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    
    public String getLocation() {
    	return location;
    }
    
    public void setLocation(String location) {
    	this.location = location;
    }
    
    public int getYear() {
    	return year;
    }
    
    public void setYear(int year) {
    	this.year = year;
    }
    
    @Override
    public String toString() {
    	return "Articolul: " + title + " | Id: " + id + " | Autor: " + author + " | Sursa: " + location + " | Anul: " + year;
    }
}
