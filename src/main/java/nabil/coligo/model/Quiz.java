package nabil.coligo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Entity
@Data
@NoArgsConstructor
@Builder
public class Quiz {
    public Quiz(Long id, String courseName, String topic, LocalDateTime dueTo, Set<Question> questions) {
        this.id = id;
        this.courseName = courseName;
        this.topic = topic;
        this.dueTo = dueTo;
        setQuestions(questions);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String topic;

    private LocalDateTime dueTo;

    @Builder.Default
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();
    public void setQuestions(Set<Question> questions) {
        this.questions = new HashSet<>();
        questions.forEach(this::addQuestion);
    }

    public void addQuestion(Question question) {
        question.setQuiz(this);
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setQuiz(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id != null && Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", topic='" + topic + '\'' +
                ", dueTo=" + dueTo +
                '}';
    }
}
