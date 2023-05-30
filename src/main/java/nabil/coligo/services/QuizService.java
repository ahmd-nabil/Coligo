package nabil.coligo.services;

import nabil.coligo.model.Quiz;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface QuizService {
    Page<Quiz> findAll(Integer pageNumber, Integer pageSize);
    Optional<Quiz> findById(Long id);
    Quiz save(Quiz quiz);
    Optional<Quiz> update(Long id, Quiz quiz);
    boolean delete(Long id);
}
