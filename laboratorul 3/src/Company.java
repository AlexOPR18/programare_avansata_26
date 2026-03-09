public class Company implements Profile, Comparable<Company>{
    private String id;
    private String nume;
    private String industry;
    
    public Company(String id, String nume, String industry) {
    	this.id=id;
    	this.nume=nume;
    	this.industry=industry;
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
    public int compareTo(Company other) {
    	return this.nume.compareTo(other.nume);
    }
    
    @Override
    public String toString() {
        return "Company{" + nume + " - " + industry + '}';
    }   
}
