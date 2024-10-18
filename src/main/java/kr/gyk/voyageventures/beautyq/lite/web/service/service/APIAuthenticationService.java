package kr.gyk.voyageventures.beautyq.lite.web.service.service;

import kr.gyk.voyageventures.beautyq.lite.web.service.exception.APIAuthFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIAuthenticationService {
    @Value("${auth.api.token}") private String authToken;

    public Boolean verifyAuthToken(String token) throws Exception {
        if (compareAuthToken(token)) throw new APIAuthFailedException();
        return true;
    }

    public Boolean compareAuthToken (String token) {
        return authToken.equals(token);
    }

}
