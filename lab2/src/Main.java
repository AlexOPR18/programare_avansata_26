public class Main{
   public static void main(String[] args) {
	   City iasi = new City("Iasi", 47.15, 27.56, 760000);
	   City pascani = new City("Pascani", 47.12, 26.43, 30000);
	   City vaslui = new City("Vaslui", 200, 200, 50000);
	   // City clonaIasi = new City("Iasi", 47.15, 27.56, 760000);
	   // Airport aeroport = new Location("Aeroport Iasi", "Aeroport", 47.17, 27.62);
	   
	   Road e85 = new Road("E85",RoadType.EXPRESS, 70.5, 100, iasi, pascani);
	   // Road clonaE85 = new Road("E85",RoadType.EXPRESS, 70.5, 100, iasi, pascani);
	   Road dn28 = new Road("DN28",RoadType.COUNTRY, 128.3, 50, pascani, iasi);
	   
	   Problem hartaMoldovei = new Problem();
	   hartaMoldovei.addLocation(iasi);
	   hartaMoldovei.addLocation(pascani);
	   // hartaMoldovei.addLocation(clonaIasi);
	   hartaMoldovei.addRoad(e85);
	   // hartaMoldovei.addRoad(clonaE85);
	   hartaMoldovei.isValid();
	   
	   System.out.println("Cautam rute...");
	   System.out.println("Iasi -> Pascanii exista?" + hartaMoldovei.canReach(iasi, pascani));
	   System.out.println("Iasi -> Vaslui exista?" + hartaMoldovei.canReach(iasi, vaslui));
   }
}