import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Commmand {
    private Resource item;
    
    public ViewCommand(Resource item) {
    	this.item = item;
    }
    
    @Override
    public void execute() throws InvalidCommandException {
    	try {
        	if(!Desktop.isDesktopSupported()) {
        		throw new InvalidCommandException("N-avem Desktop");
        	}
        	
        	Desktop desktop = Desktop.getDesktop();
        	String location = item.getLocation();
        	
        	if(location.startsWith("http")) {
        		desktop.browse(new URI(location));
        	  } else {
        		File file = new File(location);
        		if(file.exists()) {
        			desktop.open(file);
        		} else { throw new InvalidResourceException("N-avem documentu la locatia: " + location);
        	       }
              }
            } catch (Exception e) {
            	System.out.println("Eroare la Browser: " + e.getMessage());
    	}
    }
    
}
