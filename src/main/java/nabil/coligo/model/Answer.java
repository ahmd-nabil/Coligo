package nabil.coligo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @Builder.Default
    private Boolean correct = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    private void setQuestion(Question question) {
        this.question = question;
        question.getAnswers().add(this);
    }
}
