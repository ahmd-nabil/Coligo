package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.QuizAllDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizGetDto;
import nabil.coligo.exceptions.QuizNotFoundException;
import nabil.coligo.mappers.QuizMapper;
import nabil.coligo.model.Quiz;
import nabil.coligo.repositories.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
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
    @Transactional
    public Optional<Quiz> update(Long id, Quiz quiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isEmpty())
            return optionalQuiz;
        Quiz foundQuiz = optionalQuiz.get();
        foundQuiz.setCourseName(quiz.getCourseName());
        foundQuiz.setTopic(quiz.getTopic());
        foundQuiz.setDueTo(quiz.getDueTo());
        foundQuiz.setQuestions(quiz.getQuestions());
        return Optional.of(foundQuiz);
    }

    @Override
    public boolean delete(Long id) {
        if(quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
