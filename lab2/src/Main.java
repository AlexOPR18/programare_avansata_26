public class Main{
   public static void main(String[] args) {
	   Location iasi = new Location("Iasi", "Oras", 47.15, 27.56);
	   Location pascani = new Location("Pascani", "Oras", 47.12, 26.43);
	   Location aeroport = new Location("Aeroport Iasi", "Aeroport", 47.17, 27.62);
	   
	   Road e85 = new Road("E85", "Drum express" , 70.5, 100);
	   Road dn28 = new Road("DN28", "Drum de tara", 128.3, 50);
	   
	   System.out.println(iasi);
	   System.out.println(pascani);
	   System.out.println(aeroport);
	   System.out.println(e85);
	   System.out.println(dn28);
   }
}