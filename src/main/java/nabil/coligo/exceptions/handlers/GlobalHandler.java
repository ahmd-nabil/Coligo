package nabil.coligo.exceptions.handlers;

import nabil.coligo.exceptions.AnnouncementNotFoundException;
import nabil.coligo.exceptions.ForbiddenDataAccessException;
import nabil.coligo.exceptions.QuizNotFoundException;
import nabil.coligo.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ahmed Nabil
 */
@RestControllerAdvice
public class GlobalHandler {

    // global handler to return only the message of the exception
    @ExceptionHandler({ForbiddenDataAccessException.class, AnnouncementNotFoundException.class, QuizNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<String> forbiddenDataAccess(ForbiddenDataAccessException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
