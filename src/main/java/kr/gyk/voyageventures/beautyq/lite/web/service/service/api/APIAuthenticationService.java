package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.exception.APIAuthFailedException;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.APIUnAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIAuthenticationService {
    @Value("${auth.api.token}") private String authToken;

    public Boolean verifyAuthToken(String token) {
        if (token == null) throw new APIUnAuthException();
        else if (!compareAuthToken(token)) throw new APIAuthFailedException();
        return true;
    }

    public Boolean compareAuthToken (String token) {
        return authToken.equals(token);
    }

}
