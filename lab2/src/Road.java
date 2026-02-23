public class Road{
	private String name;
	private String type;
	private double length;
	private int speedLimit;
	
	public Road(String name, String type, double length, int speedLimit) {
		this.name = name;
		this.type= type;
		this.length = length;
		this.speedLimit = speedLimit; 
	}
	public String getName() {
		return name;
	}
	public void setName() {
		this.name=name;
	}
	public String getType() {
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
	
	@Override
	public String toString() {
		return "Road { "+ name + " | tip : " + type + " | lungime : " + length + "| Limita Viteza : " + speedLimit + " }";
	}
}