package jdbc_app; 

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genreDAO = new GenreDAO();
            ActorDAO actorDAO = new ActorDAO();
            MovieDAO movieDAO = new MovieDAO();

            Genre actionGenre = genreDAO.findByName("Action");
            if (actionGenre == null) {
                genreDAO.create("Action");
                actionGenre = genreDAO.findByName("Action");
            }
            System.out.println("Am gasit/creat genul: " + actionGenre.getName() + " (ID: " + actionGenre.getID() + ")");

            Actor keanu = actorDAO.findByName("Keanu Reeves");
            if (keanu == null) {
                actorDAO.create("Keanu Reeves");
                keanu = actorDAO.findByName("Keanu Reeves");
            }
            System.out.println("Am gasit/creat actorul: " + keanu.getName() + " (ID: " + keanu.getID() + ")");

            Movie johnWick = movieDAO.findByTitle("John Wick");
            if (johnWick == null) {
                movieDAO.create("John Wick", LocalDate.of(2014, 10, 24), 101, 7.4, actionGenre.getID());
                johnWick = movieDAO.findByTitle("John Wick");
            }
            System.out.println("Am gasit/creat filmul: " + johnWick.getTitle() + " (ID: " + johnWick.getID() + ")");

            try {
                movieDAO.addActorToMovie(johnWick.getID(), keanu.getID());
                System.out.println("SUCCES! Am legat actorul '" + keanu.getName() + "' de filmul '" + johnWick.getTitle() + "'.");
            } catch (SQLException e) {
                System.out.println("Info: Legatura dintre actor si film exista deja in baza de date.");
            }

        } catch (SQLException e) {
            System.err.println("Eroare de DB: " + e.getMessage());
            e.printStackTrace();
        } finally {
        	System.out.println("\nGeneram raportul HTML...");
            ReportGenerator report = new ReportGenerator();
            report.generateHTML();
            Database.getInstance().closePool();
            System.out.println("\nConexiunea la DB a fost inchieiata.");
        }
    }
}