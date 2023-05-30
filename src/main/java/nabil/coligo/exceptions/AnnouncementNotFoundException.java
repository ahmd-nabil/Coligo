package nabil.coligo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ahmed Nabil
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Announcement !")
public class AnnouncementNotFoundException extends RuntimeException{
}
