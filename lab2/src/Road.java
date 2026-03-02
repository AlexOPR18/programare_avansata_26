public class Road{
	private String name;
	private RoadType type;
	private double length;
	private int speedLimit;
	private Location start;
	private Location end;
	
	public Road(String name, RoadType type, double length, int speedLimit, Location start, Location end) {
		this.name = name;
		this.type= type;
		this.length = length;
		this.speedLimit = speedLimit; 
	    this.start= start;
	    this.end= end;
	}
	public String getName() {
		return name;
	}
	public void setName() {
		this.name=name;
	}
	public RoadType getType() {
		return type;
	}
	public void setType() {
		this.type= type;
	}
	public double getLength() {
		return length;
	}
	public void setLength() {
		this.length = length;
	}
	public int speedLimit() {
		return speedLimit;
	}
	public void setSpeedLimit() {
		this.speedLimit = speedLimit;
	}
	
	public Location getStart() {
		return start;
	}
	
	public void setStart() {
		this.start= start;
	}
	
	public Location getEnd() {
		return end;
	}
	
	public void setEnd() {
		this.end=end;
	}
	@Override
	public String toString() {
		return "Road { "+ name + " | tip : " + type + " | lungime : " + length + "| Limita Viteza : " + speedLimit + " }";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null || getClass() != obj.getClass()) return false;
		Road road = (Road) obj;
		boolean sameDirection =this.start.equals(road.start) && this.end.equals(road.end);
		boolean oppositeDirection = this.start.equals(road.end) && this.end.equals(road.start);
		return sameDirection || oppositeDirection;
	}
	
	@Override
	public int hashCode() {
		return start.hashCode() + end.hashCode();
	}
}