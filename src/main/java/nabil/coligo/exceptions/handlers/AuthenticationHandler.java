package nabil.coligo.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ahmed Nabil
 */
@RestControllerAdvice
public class AuthenticationHandler {
    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    public ResponseEntity<String> badCredentials(Exception exception) {
        return new ResponseEntity<>("Email or Password not good !", HttpStatus.UNAUTHORIZED);
    }
}
