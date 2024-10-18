package kr.gyk.voyageventures.beautyq.lite.web.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity Code Duplicated Exception")
public class EntityCodeDuplicatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityCodeDuplicatedException (String message) { super(message); }
    public EntityCodeDuplicatedException () { super("Entity Code Duplicated Exception"); }
}