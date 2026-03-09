public class Programmer extends Person{
    private String favoriteLanguage;
    public Programmer(String id, String nume, String birthDate, String favoriteLanguage) {
    	super(id, nume, birthDate);
    	this.favoriteLanguage = favoriteLanguage;
    }
    
    public String getFavoriteLanguage() {
    	return favoriteLanguage;
    }
    
    @Override
    public String toString() {
    	return "Programmer{" + getName() + " scrie in " + favoriteLanguage + "}";
    }
}
