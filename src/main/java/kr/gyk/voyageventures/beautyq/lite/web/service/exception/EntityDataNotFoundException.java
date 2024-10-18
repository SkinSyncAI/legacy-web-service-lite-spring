package kr.gyk.voyageventures.beautyq.lite.web.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found Exception")
public class EntityDataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityDataNotFoundException(String message) { super(message); }
    public EntityDataNotFoundException() { super("Entity Not Found Exception"); }
}