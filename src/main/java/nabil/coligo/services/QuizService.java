package nabil.coligo.services;

import nabil.coligo.dtos.QuizAllDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizGetDto;
import nabil.coligo.dtos.QuizUpdateDto;
import nabil.coligo.model.Quiz;
import org.springframework.data.domain.Page;

/**
 * @author Ahmed Nabil
 */
public interface QuizService {
    Page<QuizAllDto> findAll(Integer pageNumber, Integer pageSize);
    QuizGetDto findById(Long id);
    Quiz save(QuizCreateDto dto);
    Quiz update(Long id, QuizUpdateDto quiz);
    boolean delete(Long id);
}
