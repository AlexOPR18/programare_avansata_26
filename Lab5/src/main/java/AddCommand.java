public class AddCommand implements Commmand{
	private Repo catalog;
	private Resource item;
   
	public AddCommand(Repo catalog, Resource item) {
		this.catalog = catalog;
		this.item = item;
	}
	@Override
	public void execute() throws InvalidCommandException {
	    try {
		    catalog.add(item);
	        System.out.println("Am adaugat " + item.getTitle() + " cu succes!");
	    } catch (InvalidResourceException e) {
	    	throw new InvalidCommandException("Eroare la adaugare: " + e.getMessage());
	    }
    }
}