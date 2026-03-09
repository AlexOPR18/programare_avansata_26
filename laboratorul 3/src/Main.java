public class Main {
    public static void main(String[] args) {
    	Programmer prog1 = new Programmer("P-01", "Oprea Marian", "18-01-2006", "Java");
    	Designer des1 = new Designer("D-01", "Pirtac Stefan", "09-07-2005", "FACEIT");
    	Company comp1 = new Company("COMP-01", "CODEX VAG", "Codari Auto");
    	Company comp2 = new Company("COMP-02", "MAOPR GAMES", "Jocuri Video");
    	
    	prog1.addRelationship(comp2, "angajat");
    	prog1.addRelationship(des1, "prieten");
    	
    	des1.addRelationship(comp1,"colaborator");
    	des1.addRelationship(prog1, "coleg de facultate");
    	
    	SocialNetwork network = new SocialNetwork();
    	network.addProfile(prog1);
    	network.addProfile(des1);
    	network.addProfile(comp1);
    	network.addProfile(comp2);
    	
    	network.printNetwork();
    }
}