public abstract sealed class Location permits City{
	private String name;
	private String type;
	private double x;
	private double y;
	
	public Location(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getX() {
		return x;
	}
	public void setX() {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY() {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Location { " + "nume= '" + name + "' | tip : " + type + "| x : " + x + "|  y : " + y + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(obj== null || getClass() != obj.getClass()) return false;
		Location location = (Location) obj;
		return name.equals(location.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}