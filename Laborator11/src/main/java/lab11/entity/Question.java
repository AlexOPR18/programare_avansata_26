package lab11.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String answer;

    @ManyToMany(mappedBy = "questions")
    private List<Game> games;

    public Question() {}
    public Question(String text, String answer) { this.text = text; this.answer = answer; }

    public String getText() { return text; }
    public String getAnswer() { return answer; }
}