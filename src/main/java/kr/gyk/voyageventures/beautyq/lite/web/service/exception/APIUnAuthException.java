package kr.gyk.voyageventures.beautyq.lite.web.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "UnAuthorized Exception")
public class APIUnAuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public APIUnAuthException (String message) { super(message); }
    public APIUnAuthException () { super("UnAuthorized Exception"); }
}