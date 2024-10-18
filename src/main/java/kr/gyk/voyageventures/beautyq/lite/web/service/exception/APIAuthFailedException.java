package kr.gyk.voyageventures.beautyq.lite.web.service.exception;

import kr.gyk.voyageventures.beautyq.lite.web.service.service.APIAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Authentication Failed Exception")
public class APIAuthFailedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public APIAuthFailedException(String message) { super(message); }
    public APIAuthFailedException() { super("Authentication Failed Exception"); }
}