package nabil.coligo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nabil.coligo.dtos.AnswerCreateDto;
import nabil.coligo.dtos.QuestionCreateDto;
import nabil.coligo.dtos.QuizCreateDto;
import nabil.coligo.dtos.QuizUpdateDto;
import nabil.coligo.mappers.QuizMapper;
import nabil.coligo.model.*;
import nabil.coligo.services.QuizService;
import nabil.coligo.services.auth.JwtService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ahmed Nabil
 */
@WebMvcTest(QuizController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(JwtService.class)
class QuizControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuizService quizService;

    @Autowired
    ObjectMapper objectMapper;
    private static String token;

    QuizMapper quizMapper = Mappers.getMapper(QuizMapper.class);
    @BeforeAll
    static void beforeAll() {
        User user = User.builder()
                .id(0L)
                .firstName("Ahmed")
                .lastName("Nabil")
                .email("ahmednabil@gmail.com")
                .password("123")
                .role(Role.INSTRUCTOR)
                .build();
        token = "Bearer "+new JwtService().generateToken(user);
    }

    Quiz quiz;
    QuizCreateDto quizCreateDto;
    QuizUpdateDto quizUpdateDto;
    @BeforeEach
    void setUp() {
        Answer answer1 = Answer.builder().id(1L)
                .answer("A1")
                .build();
        Answer answer2 = Answer.builder().id(2L)
                .answer("A2")
                .build();
        Answer answer3 = Answer.builder().id(3L)
                .answer("A3")
                .build();
        Question question1 = Question.builder().id(1L)
                .question("q1?")
                .answers(new HashSet<>(Arrays.asList(answer1, answer2)))
                .build();
        Question question2 = Question.builder().id(2L)
                .question("q2?")
                .answers(Collections.singleton(answer3))
                .build();
        Question question3 = Question.builder().id(3L)
                .question("What are arrays?")
                .build();
        quiz = Quiz.builder().id(1L)
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(question1, question2, question3))).build();

        QuestionCreateDto questionCreateDto1 = QuestionCreateDto.builder()
                .question("q1?")
                .build();
        QuestionCreateDto questionCreateDto2 = QuestionCreateDto.builder()
                .question("q2?")
                .build();
        AnswerCreateDto answerCreateDto1 = AnswerCreateDto.builder()
                .answer("A1")
                .build();
        AnswerCreateDto answerCreateDto2 = AnswerCreateDto.builder()
                .answer("A2")
                .build();
        AnswerCreateDto answerCreateDto3 = AnswerCreateDto.builder()
                .answer("A3")
                .build();

        QuestionCreateDto questionCreateDto3 = QuestionCreateDto.builder()
                .question("q3?")
                .answers(new HashSet<>(Arrays.asList(answerCreateDto1, answerCreateDto2, answerCreateDto3)))
                .build();

        quizCreateDto = QuizCreateDto.builder()
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(questionCreateDto1, questionCreateDto2, questionCreateDto3))).build();
        quizUpdateDto = quizMapper.toQuizUpdateDto(quiz);

    }

    @Test
    void testSaveQuiz() throws Exception {
        // given
        given(quizService.save(any())).willReturn(quiz);
        mockMvc.perform(
                post("/api/v1/quizzes")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(quizCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdateQuiz() throws Exception {
        // given
        given(quizService.update(any(), any())).willReturn(Optional.of(quiz));

        mockMvc.perform(
                put("/api/v1/quizzes/1")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(quizUpdateDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteQuiz() throws Exception {
        // given
        given(quizService.delete(any())).willReturn(true);

        mockMvc.perform(
                delete("/api/v1/quizzes/1")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateQuizFails() throws Exception {
        // given
        given(quizService.update(any(), any())).willReturn(Optional.empty());
        mockMvc.perform(
                put("/api/v1/quizzes/11")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(quizUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteQuizFails() throws Exception {
        // given
        given(quizService.delete(any())).willReturn(false);

        mockMvc.perform(
                delete("/api/v1/quizzes/11")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}