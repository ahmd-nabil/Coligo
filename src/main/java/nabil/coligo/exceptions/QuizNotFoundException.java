package nabil.coligo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ahmed Nabil
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Quiz !")
public class QuizNotFoundException extends RuntimeException{
}
