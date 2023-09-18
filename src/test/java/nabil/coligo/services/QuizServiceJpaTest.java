package nabil.coligo.services;

import nabil.coligo.dtos.*;
import nabil.coligo.mappers.QuizMapper;
import nabil.coligo.model.Answer;
import nabil.coligo.model.Question;
import nabil.coligo.model.Quiz;
import nabil.coligo.repositories.QuizRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Ahmed Nabil
 */
@ExtendWith(MockitoExtension.class)
class QuizServiceJpaTest {
    @Mock
    QuizRepository quizRepository;
    @Mock
    QuizMapper quizMapper;
    @InjectMocks
    QuizServiceJpa quizServiceJpa;

    @Test
    void testFindAll() {
        Question question1 = Question.builder()
                .question("q1?")
                .build();
        Question question2 = Question.builder()
                .question("q2?")
                .build();
        Answer answer1 = Answer.builder()
                .answer("A1")
                .build();
        Answer answer2 = Answer.builder()
                .answer("A2")
                .build();
        Answer answer3 = Answer.builder()
                .answer("A3")
                .build();

        Question question3 = Question.builder()
                .question("q3?")
                .answers(new HashSet<>(Arrays.asList(answer1, answer2, answer3)))
                .build();
        Quiz quiz = Quiz.builder()
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(question1, question2, question3))).build();

        QuizAllDto dto = QuizAllDto.builder()
                .id(1L)
                .courseName(quiz.getCourseName())
                .topic(quiz.getTopic())
                .dueTo(quiz.getDueTo())
                .build();

        Mockito.when(quizRepository.findAllByOrderByDueTo(any())).thenReturn(new PageImpl<>(List.of(quiz)));
        Mockito.when(quizMapper.toQuizAllDto(any())).thenReturn(dto);

        Page<QuizAllDto> result = quizServiceJpa.findAll(1, 10);
        Assertions.assertThat(result.getTotalElements()).isEqualTo(1);
        Mockito.verify(quizMapper, Mockito.times(result.getTotalPages())).toQuizAllDto(any());
        Mockito.verify(quizRepository).findAllByOrderByDueTo(any());
    }
    @Test
    void saveQuizWithMultipleQuestions() {
        QuestionCreateDto question1 = QuestionCreateDto.builder()
                .question("q1?")
                .build();
        QuestionCreateDto question2 = QuestionCreateDto.builder()
                .question("q2?")
                .build();
        AnswerCreateDto answer1 = AnswerCreateDto.builder()
                .answer("A1")
                .build();
        AnswerCreateDto answer2 = AnswerCreateDto.builder()
                .answer("A2")
                .build();
        AnswerCreateDto answer3 = AnswerCreateDto.builder()
                .answer("A3")
                .build();

        QuestionCreateDto question3 = QuestionCreateDto.builder()
                .question("q3?")
                .answers(new HashSet<>(Arrays.asList(answer1, answer2, answer3)))
                .build();

        QuizCreateDto quiz1 = QuizCreateDto.builder()
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(question1, question2, question3))).build();
        QuizGetDto saved = quizServiceJpa.save(quiz1);
    }
}