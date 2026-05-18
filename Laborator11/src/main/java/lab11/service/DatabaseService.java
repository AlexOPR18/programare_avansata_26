package lab11.service;

import lab11.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.logging.*;

@Service
public class DatabaseService {
    private final PlayerRepository playerRepository;
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());

    public DatabaseService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        try {
            FileHandler fh = new FileHandler("jpql_execution.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void executeAndLogQueries() {
        long startTime = System.currentTimeMillis();
        try {
            playerRepository.updatePlayerName(1L, "Gigel Pro");
            
            long endTime = System.currentTimeMillis();
            logger.info("UPDATE Query executat in: " + (endTime - startTime) + " ms");

        } catch (Exception e) {
            logger.severe("Eroare la executia JPQL: " + e.getMessage());
        }
    }
}