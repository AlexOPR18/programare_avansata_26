import java.util.ArrayList;
import java.util.List;

public class Repo {
    private String repoName;
    private List<Resource> items;
    
    public Repo(String repoName) {
    	this.repoName = repoName;
    	this.items = new ArrayList<>();
    }
    
    public String getRepoName() {
    	return repoName;
    }
    
    public void add(Resource item) throws InvalidResourceException {
    	if(item == null || item.getId() == null) {
    		throw new InvalidResourceException("Resursa invalida sau Lipsa Id");
    	}
    	items.add(item);
    }
    
    public List<Resource> getItems(){
    	return items;
    }
    
    public void DisplayItems() {
    	System.out.println("Catalog: " + repoName);
    	for(Resource item : items) {
    		System.out.println(item);
    	}
    }
}
