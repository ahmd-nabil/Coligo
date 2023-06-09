package nabil.coligo.controllers;

import lombok.RequiredArgsConstructor;
import nabil.coligo.exceptions.QuizNotFoundException;
import nabil.coligo.model.Quiz;
import nabil.coligo.services.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public Page<Quiz> findAll(@RequestParam(required = false) Integer pageNumber,
                              @RequestParam(required = false) Integer pageSize) {
        return quizService.findAll(pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<URI> saveQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.save(quiz);
        return ResponseEntity.created(URI.create("/api/v1/quizzes/" + savedQuiz.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable(name = "id") Long id, @RequestBody Quiz quiz) {
        quizService.update(id, quiz).orElseThrow(QuizNotFoundException::new);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable(name = "id") Long id) {
        boolean deleted = quizService.delete(id);
        if(deleted) return ResponseEntity.ok().build();
        throw new QuizNotFoundException();
    }
}
