package nabil.coligo.services;

import nabil.coligo.model.Answer;
import nabil.coligo.model.Question;
import nabil.coligo.model.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Ahmed Nabil
 */
@SpringBootTest
@AutoConfigureTestDatabase
class QuizServiceJpaTest {
    @Autowired
    QuizServiceJpa quizService;

    @Test
    void saveQuizWithMultipleQuestions() {
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
        Quiz quiz1 = Quiz.builder()
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(question1, question2, question3))).build();
        Quiz saved = quizService.save(quiz1);
        System.out.println(saved);

    }
}