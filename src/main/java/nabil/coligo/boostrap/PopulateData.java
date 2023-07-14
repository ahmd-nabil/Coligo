package nabil.coligo.boostrap;

import lombok.RequiredArgsConstructor;
import nabil.coligo.model.*;
import nabil.coligo.repositories.QuizRepository;
import nabil.coligo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Ahmed Nabil
 */
@Component
@RequiredArgsConstructor
public class PopulateData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() < 1) {
            addUserAndAnnouncements();
        }

        if(quizRepository.count() < 1) {
            addQuizzes();
        }
    }

    private void addQuizzes() {
        Answer answer1 = Answer.builder()
                .answer("A1")
                .build();
        Answer answer2 = Answer.builder()
                .answer("A2")
                .build();
        Answer answer3 = Answer.builder()
                .answer("A3")
                .build();

        Question question1 = Question.builder()
                .question("q1?")
                .answers(new HashSet<>(Arrays.asList(answer1, answer2)))
                .build();
        Question question2 = Question.builder()
                .question("q2?")
                .answers(Collections.singleton(answer3))
                .build();
        Question question3 = Question.builder()
                        .question("What are arrays?")
                        .build();



        Quiz quiz = Quiz.builder()
                .courseName("Algo")
                .topic("Arrays")
                .dueTo(LocalDateTime.parse("2023-05-31T01:30:00"))
                .questions(new HashSet<>(Arrays.asList(question1, question2, question3))).build();
        quizRepository.save(quiz);
        System.out.println();
    }

    private void addUserAndAnnouncements() {
        User user = userRepository.save(User.builder()
                .firstName("Ahmed")
                .lastName("Nabil")
                .email("ahmednabil@gmail.com")
                .password(passwordEncoder.encode("123"))
                .role(Role.INSTRUCTOR)
                .build());
        Announcement announcement1 = Announcement.builder()
                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
                .build();
        Announcement announcement2 = Announcement.builder()
                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
                .build();
        Announcement announcement3 = Announcement.builder()
                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
                .build();
        Announcement announcement4 = Announcement.builder()
                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
                .build();
        user.addAnnouncement(announcement1);
        user.addAnnouncement(announcement2);
        user.addAnnouncement(announcement3);
        user.addAnnouncement(announcement4);
        userRepository.save(user);
    }
}
