public class Problem {
	private Location[] locations = new Location[100];
	private int locationsCount = 0;
	private Road[] roads = new Road[100];
	private int roadsCount = 0;
	
	public void addLocation(Location loc) {
		for(int i=0; i<locationsCount; i++) {
			if(locations[i].equals(loc)) {
				System.out.println(" Eroare: Locatia '" + loc.getName() + "'exista deja pe harta!");
				return;
			}
		}
		locations[locationsCount++] = loc;
		System.out.println("+ Locatie adaugata: " + loc.getName());
	}
	
	public void addRoad(Road road) {
		for(int i = 0;i<roadsCount; i++) {
			if(roads[i].equals(road)) {
				System.out.println(" Eroare: Drumul '" + road.getName() + "' exista deja! ");
				return;
			}
		}
		roads[roadsCount++]=road;
		System.out.println("+ Drum adaugat: " + road.getName());
	}
	
	public boolean isValid() {
		for(int i = 0; i<roadsCount;i++) {
			Road r = roads[i];
			Location start = r.getStart();
			Location end = r.getEnd();
			
            double distance = Math.sqrt(Math.pow(start.getX()-end.getX(), 2) + Math.pow(start.getY()-end.getY(),2));		
		    if(r.getLength() < distance) {
		    	System.out.println("\n[X] Drumul " + r.getName() + "e sf." +
		                           "Lungime declarata: " + r.getLength() +
		                           " | Distanta: " + String.format("%.2f", distance));
		    	return false;
		    }
		}
		System.out.println("\n[V] Problema este corecta. Drumurile sunt bune.");
		return true;
	}
	
	public boolean canReach(Location start, Location destination) {
		boolean[] visited = new boolean[locationsCount];
		return dfs(start, destination, visited);
	}
	
	private boolean dfs(Location current, Location destination, boolean[] visited) {
		if(current.equals(destination)) return true;
		int currentIndex = -1;
		for(int i=0; i<locationsCount; i++) {
			if(locations[i].equals(current)) {
				currentIndex = 1;
				break;
			}
		}
		
		if(currentIndex == -1 || visited[currentIndex]) return false;
		visited[currentIndex]=true;
		for(int i = 0; i<roadsCount; i++) {
			Road r = roads[i];
			Location nextCity = null;
			if(r.getStart().equals(current)) {
				nextCity= r.getEnd();
			}else if (r.getEnd().equals(current)) {
				nextCity = r.getStart();
			}
			if(nextCity!=null) {
				if(dfs(nextCity, destination, visited)){
					return true;
				}
			}
		}
	    return false;
	}
	
	
	
}
