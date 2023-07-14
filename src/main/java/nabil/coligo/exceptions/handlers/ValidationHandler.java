package nabil.coligo.exceptions.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmed Nabil
 */
@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> controllerValidationHandler(MethodArgumentNotValidException exception) {
        List<String> errorsMessages = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> errorsMessages.add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("errors", errorsMessages));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> entityValidationHandler(ConstraintViolationException exception) {
        List<String> errorsMessages = new ArrayList<>();
        exception.getConstraintViolations().forEach(violation -> errorsMessages.add(violation.getMessage()));
        return ResponseEntity.badRequest().body(Map.of("errors", errorsMessages));
    }
}
