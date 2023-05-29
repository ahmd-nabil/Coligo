package nabil.coligo.repositories;

import nabil.coligo.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
