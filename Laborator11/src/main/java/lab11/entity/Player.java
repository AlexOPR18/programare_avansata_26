package lab11.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
@EntityListeners(AuditingEntityListener.class) 
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Result> results;

    @CreatedDate 
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Player() {}
    public Player(String name) { this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}