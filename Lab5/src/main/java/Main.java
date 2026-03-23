public class Main {
    public static void main(String[] args) {
    	Repo catalog = new Repo("Biblioteca din camara");
    	
    	Book book1 = new Book("b01", "Capra cu trei iezi", "D:/Povesti/capracutreiiezi.docx", 1875, "Ion Creanga");
    	Article art1 = new Article("art01", "Invatare Java", "https://www.w3schools.com/java/default.asp", 2024, "Marian");
    	Article art2 = new Article("art02", "Simple Game in LibGDX", "https://www.youtube.com/watch?v=aipDYyh1Mlc", 2024 , "Raeleus");
    	
    	try {
    		Commmand adaugaArticol = new AddCommand(catalog, art1);
    		Commmand adaugaCarte = new AddCommand(catalog, book1);
    		adaugaArticol.execute();
    		adaugaCarte.execute();
    		
    		Commmand afiseazaTot = new ListCommmand(catalog);
    		afiseazaTot.execute();
    		
    		Commmand genereazaRaport = new ReportCommand(catalog);
    		genereazaRaport.execute();
    		
    		Commmand veziDocument = new ViewCommand(art1);
    		veziDocument.execute();
    		
    	} catch (InvalidCommandException e) {
    		System.err.println("Eroare la comanda: " + e.getMessage());
    	} catch (Exception e) {System.err.println("Nu stim ce are" + e.getMessage());
    }
  }
}
