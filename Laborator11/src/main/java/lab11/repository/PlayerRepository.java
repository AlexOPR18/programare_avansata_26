package lab11.repository;

import lab11.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.name LIKE %:keyword%")
    List<Player> findPlayersByNameContaining(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Player p SET p.name = :newName WHERE p.id = :id")
    int updatePlayerName(@Param("id") Long id, @Param("newName") String newName);
}