package lab11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lab11.entity.Player;
import lab11.entity.Question;
import lab11.repository.PlayerRepository;
import lab11.repository.QuestionRepository;
import lab11.service.DatabaseService;
import lab11.service.GameServer;

@SpringBootApplication
@EnableJpaAuditing
public class Lab11Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab11Application.class, args);
    }

    @Bean
    CommandLineRunner testApplication(PlayerRepository playerRepo, QuestionRepository questionRepo, GameServer gameServer, DatabaseService dbService) {
        return args -> {
            System.out.println("--- Configuram jocul in BAZA DE DATE---");
            
            questionRepo.save(new Question("Care este capitala Italiei?", "Roma"));
            questionRepo.save(new Question("In ce an a inceput Al Doilea Razboi Mondial?", "1939"));
            questionRepo.save(new Question("Care este cel mai mare ocean de pe Pamant?", "Pacific"));
            
            System.out.println("Intrebarile au fost salvate. Pornim Serverul de retea...");
            new Thread(gameServer::start).start();
            
            playerRepo.save(new Player("Alex"));
            playerRepo.save(new Player("Stefan"));

            System.out.println("Jucatori gasiti cu JPQL:");
            playerRepo.findPlayersByNameContaining("e").forEach(p -> 
                System.out.println(p.getName())
            );

            dbService.executeAndLogQueries();
            
            System.out.println("--- GATA ---");
        };
    }
}