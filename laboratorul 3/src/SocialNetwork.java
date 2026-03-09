import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SocialNetwork {
    private List<Profile> profiles = new ArrayList<>();
    public void addProfile(Profile p) {
    	profiles.add(p);
    }
    
    public int computeImportance(Profile target) {
    	int importance = 0;
    	if(target instanceof Person) {
    		Person p = (Person) target;
    		importance += p.getRelationships().size();
    	}
    	
    	for(Profile profile : profiles) {
    		if(profile instanceof Person && profile != target) {
    			Person otherPerson = (Person) profile;
    		    if(otherPerson.getRelationships().containsKey(target)) {
    		    	importance++;
    		    }
    		}
    	}
    	return importance;
    }
    
    public void printNetwork() {
    	profiles.sort(new Comparator<Profile>() {
    		@Override
    		public int compare(Profile p1, Profile p2) {
    			return Integer.compare(computeImportance(p2), computeImportance(p1));
    		}
    	});
    	
    	System.out.println("Reteaua Sociala :");
    	for(Profile p : profiles) {
    		System.out.println(p.toString() + "Importanta: " + computeImportance(p));
    	}
    }
}
