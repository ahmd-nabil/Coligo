package nabil.coligo.services;

import nabil.coligo.dtos.QuizAllDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizGetDto;
import nabil.coligo.model.Quiz;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface QuizService {
    Page<QuizAllDto> findAll(Integer pageNumber, Integer pageSize);
    QuizGetDto findById(Long id);
    Quiz save(QuizCreateDto dto);
    Optional<Quiz> update(Long id, Quiz quiz);
    boolean delete(Long id);
}
