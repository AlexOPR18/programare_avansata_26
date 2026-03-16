public class Street implements Comparable<Street>{
    private String name;
    private double length;
    private Intersection start;
    private Intersection end;
    
    public Street(String name, double length, Intersection start, Intersection end) {
    	this.name = name;
    	this.length = length;
    	this.start = start;
    	this.end = end;
    }
    
    public double getLength() {
    	return length;
    }
    
    public String getName() {
    	return name;
    }
    
    @Override
    public int compareTo(Street other) {
    	return Double.compare(this.length, other.length);
    }
    
    @Override
    public String toString() {
    	return "Strada: " + name + ", cu lungimea " + getLength() + " conecteaza " + start.getName() + " cu " + end.getName();
    }
    
    public Intersection getStart() { return start; }
    public Intersection getEnd() { return end; }
}
