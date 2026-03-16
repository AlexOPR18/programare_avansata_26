import java.util.List;

public class City {
    private String name;
    private List<Intersection> intersections;
    private List<Street> streets;
    
    public City(String name, List<Intersection> intersections, List<Street> streets) {
    	this.name = name;
    	this.intersections = intersections;
    	this.streets = streets;
    }
    
    public List<Intersection> getIntersections() {
    	return intersections;
    }
    
    public List<Street> getStreets() {
    	return streets;
    }
    
    public String getName() {
    	return name;
    }
}
