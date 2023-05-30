package nabil.coligo.repositories;

import nabil.coligo.model.Question;
import nabil.coligo.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ahmed Nabil
 */
@DataJpaTest
class QuizRepositoryTest {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    Quiz quiz;
    Question question;
    @BeforeEach
    void setUp() {
            quiz = Quiz.builder()
                    .courseName("Algo")
                    .topic("Arrays")
                    .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                    .build();
            question = Question.builder()
                    .question("What are arrays?")
                    .build();
    }

    @Test
    void QuizAndQuestion() {
        Quiz savedQuiz = quizRepository.save(quiz);
        Question savedQuestion = questionRepository.save(question);
        savedQuiz.addQuestion(savedQuestion);
        quizRepository.flush();
        assertThat(quizRepository.findAll().get(0).getQuestions().contains(savedQuestion)).isTrue();
        assertThat(questionRepository.count()).isEqualTo(1);
        assertThat(questionRepository.findAll().get(0).getQuiz()).isEqualTo(savedQuiz);
    }

    @Test
    void testDeleteQuizCascade() {
        // given
        Quiz savedQuiz = quizRepository.save(quiz);
        Question savedQuestion = questionRepository.save(question);
        savedQuiz.addQuestion(savedQuestion);
        quizRepository.flush();
        // when
        assertThat(questionRepository.findAll().size()).isEqualTo(1);
        quizRepository.delete(savedQuiz);
        quizRepository.flush();
        //then
        assertThat(questionRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void testRemoveAnnouncementLinkOrphanRemoval() {
        // given
        Quiz savedQuiz = quizRepository.save(quiz);
        Question savedQuestion = questionRepository.save(question);
        savedQuiz.addQuestion(savedQuestion);
        quizRepository.flush();
        // when
        assertThat(savedQuiz.getQuestions().size()).isEqualTo(1);
        assertThat(questionRepository.findAll().size()).isEqualTo(1);
        savedQuiz.removeQuestion(savedQuestion);
        quizRepository.flush();
        //then
        assertThat(questionRepository.findAll().size()).isEqualTo(0);
    }
}