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
    private final UtilService utilService;
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
    public QuizGetDto save(QuizCreateDto quizCreateDto) {
        Quiz quiz = quizMapper.toQuiz(quizCreateDto);
        return quizMapper.toQuizGetDto(quizRepository.save(quiz));
    }

    @Override
    public QuizGetDto update(Long id, QuizUpdateDto dto) {
        Quiz persistedQuiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
//        utilService.checkIfEqualsAuthenticatedUser(persistedQuiz.getUser().getEmail());
        persistedQuiz = quizMapper.toQuiz(dto);
        quizRepository.save(persistedQuiz);
        return quizMapper.toQuizGetDto(persistedQuiz);
    }

    @Override
    public boolean delete(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
//        utilService.checkIfEqualsAuthenticatedUser(persistedQuiz.getUser().getEmail());
        quizRepository.deleteById(id);
        return true;
    }
}
