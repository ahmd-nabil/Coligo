package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.QuizAllDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizGetDto;
import nabil.coligo.dtos.QuizUpdateDto;
import nabil.coligo.exceptions.QuizNotFoundException;
import nabil.coligo.mappers.QuizMapper;
import nabil.coligo.model.Quiz;
import nabil.coligo.repositories.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceJpa implements QuizService{

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    @Override
    public Page<QuizAllDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PagingService.getPageable(pageNumber, pageSize);
        return quizRepository.findAllByOrderByDueTo(pageable).map(quizMapper::toQuizAllDto);
    }

    @Override
    public QuizGetDto findById(Long id) {
        return quizRepository.findById(id).map(quizMapper::toQuizGetDto).orElseThrow(QuizNotFoundException::new);
    }

    @Override
    public Quiz save(QuizCreateDto quizCreateDto) {
        Quiz quiz = quizMapper.toQuiz(quizCreateDto);
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Long id, QuizUpdateDto dto) {
        Quiz persistedQuiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
        persistedQuiz = quizMapper.toQuiz(dto);
        quizRepository.save(persistedQuiz);
        return persistedQuiz;
    }

    @Override
    public boolean delete(Long id) {
        if(quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }
        throw new QuizNotFoundException();
    }
}
