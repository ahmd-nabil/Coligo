package nabil.coligo.mappers;

import nabil.coligo.dtos.QuizAllDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizGetDto;
import nabil.coligo.dtos.QuizUpdateDto;
import nabil.coligo.model.Quiz;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface QuizMapper {
    QuizAllDto toQuizAllDto(Quiz quiz);
    Quiz toQuiz(QuizCreateDto dto);
    Quiz toQuiz(QuizUpdateDto dto);
    QuizGetDto toQuizGetDto(Quiz quiz);
    QuizUpdateDto toQuizUpdateDto(Quiz quiz);
}
