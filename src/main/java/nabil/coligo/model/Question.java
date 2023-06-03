package nabil.coligo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Entity
@Data
@NoArgsConstructor
@Builder
public class Question {

    public Question(Long id, String question, Set<Answer> answers, Quiz quiz) {
        this.id = id;
        this.question = question;
        setAnswers(answers);
        this.quiz = quiz;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @JsonManagedReference
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    public void setAnswers(Set<Answer> answers) {
        this.answers = new HashSet<>();
        answers.forEach(this::addAnswer);
    }

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    @JsonBackReference
    @ManyToOne
    private Quiz quiz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id != null && id.equals(question1.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", quiz=" + quiz +
                '}';
    }
}
