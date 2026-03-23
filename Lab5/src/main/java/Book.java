public class Book implements Resource {
	private String id;
    private String title;
    private String author;
    private int year;
    private String location;
    
    public Book(String id, String title, String location, int year, String author) {
    	this.id = id;
    	this.title = title;
    	this.location = location;
    	this.year = year;
    	this.author = author;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setYear(int year) {
    	this.year = year;
    }
    
    public int getYear() {
    	return year;
    }
    
    public void setLocation(String location) {
    	this.location = location;
    }
    
    public String getLocation(){
    	return location;
    }
    
    @Override
    public String toString() {
    	return "Cartea: " + title + " | ID: " + id + " | Scrisa de: " + author +" | Sursa: " + location + " | Publicata in: " + year; 
    }
}
