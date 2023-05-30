package nabil.coligo.repositories;

import nabil.coligo.model.Answer;
import nabil.coligo.model.Question;
import nabil.coligo.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ahmed Nabil
 */
@DataJpaTest
class QuestionRepositoryTest {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    Question question;
    Answer answer;
    Quiz quiz;

    @BeforeEach
    void setUp() {
        question = Question.builder()
                .question("What are Arrays?")
                .build();

        answer = Answer.builder()
                .answer("Data structures used to collect items of the same datatype at contiguous memory locations")
                .build();
    }

    @Test
    void testSaveQuestionWithAnswer() {
        Question savedQuestion = questionRepository.save(question);
        Answer savedAnswer = answerRepository.save(answer);
        savedQuestion.addAnswer(savedAnswer);
        assertThat(questionRepository.findAll().get(0).getAnswers().contains(savedAnswer)).isTrue();
        assertThat(answerRepository.count()).isEqualTo(1);
        assertThat(answerRepository.findAll().get(0).getQuestion()).isEqualTo(savedQuestion);
    }
}