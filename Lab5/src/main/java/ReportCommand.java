import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ReportCommand implements Commmand  {
    private Repo catalog;
    
    public ReportCommand(Repo catalog) {
       	this.catalog = catalog;
    }
    
    @Override
    public void execute() throws InvalidCommandException {
    	Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    	
    	try {
    		cfg.setClassForTemplateLoading(ReportCommand.class, "/");
    		Template template = cfg.getTemplate("report.ftl");
    		
    		Map<String, Object> data = new HashMap<>();
    		data.put("numeCatalog", catalog.getRepoName());
    		data.put("resurseleMele", catalog.getItems());
    		
    		File fisierHtml = new File("raport_catalog.html");
    		try (Writer out = new FileWriter(fisierHtml)) {
    			template.process(data, out);
    		}
    		System.out.println("Raport HTML generat cu succes!");
    		
    		if(Desktop.isDesktopSupported()) {
    		   Desktop.getDesktop().open(fisierHtml);
    		} else {
    			System.out.println("Cauta fisierul 'raport_catalog.html' in folderul proiectului");	
    		}
    	} catch (Exception e) {
    		throw new InvalidCommandException("Eroare la crearea raportului: " + e.getMessage());
    	}
    }
}
