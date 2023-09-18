package nabil.coligo.services;

import nabil.coligo.exceptions.ForbiddenDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed Nabil
 */
@Service
public class UtilService {
    public void checkIfEqualsAuthenticatedUser(String resourceOwner) throws ForbiddenDataAccessException {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!resourceOwner.equals(authenticatedEmail)) throw new ForbiddenDataAccessException();
    }
}
