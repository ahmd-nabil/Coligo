package nabil.coligo.repositories;

import nabil.coligo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
