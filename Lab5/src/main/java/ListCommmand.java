public class ListCommmand implements Commmand {
    private Repo catalog;
    
    public ListCommmand(Repo catalog) {
    	this.catalog = catalog;
    }
    
    @Override
    public void execute() throws InvalidCommandException {
    	catalog.DisplayItems();
    }
}
