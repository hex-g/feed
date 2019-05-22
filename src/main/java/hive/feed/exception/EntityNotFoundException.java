package hive.feed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Publication not found")
public class EntityNotFoundException extends RuntimeException {
}
