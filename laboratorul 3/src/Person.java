import java.util.HashMap;
import java.util.Map;

public class Person implements Profile, Comparable<Person> {
    private String id;
    private String nume;
    private String birthDate;
    
    private Map<Profile, String> relationships;
    
    public Person(String id, String nume, String birthDate) {
    	this.id=id;
    	this.nume=nume;
    	this.birthDate = birthDate;
    	this.relationships = new HashMap<>();
    }
    
    public void addRelationship(Profile p, String relationType) {
    	this.relationships.put(p, relationType);
    }
    
    public Map<Profile, String> getRelationships(){
    	return this.relationships;
    }
    
    @Override
    public String getID() {
    	return this.id;
    }
    
    @Override
    public String getName() {
    	return this.nume;
    }
    
    @Override
    public int compareTo(Person other) {
    	return this.nume.compareTo(other.nume);
    }
    
    @Override
    public String toString() {
    	return "Person{" + nume + ", nascut: "+ birthDate + "}";
    }
}
