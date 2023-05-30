package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.exceptions.QuizNotFoundException;
import nabil.coligo.model.Quiz;
import nabil.coligo.repositories.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Override
    public Page<Quiz> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PagingService.buildPageRequest(pageNumber, pageSize);
        return quizRepository.findAllByOrderByDueTo(pageRequest);
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    @Transactional
    public Optional<Quiz> update(Long id, Quiz quiz) {
        Quiz foundQuiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
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
