package nabil.coligo.exceptions;

/**
 * @author Ahmed Nabil
 */
public class ForbiddenDataAccessException extends RuntimeException {

    public ForbiddenDataAccessException() {
        super("Forbidden Access.");
    }
}
