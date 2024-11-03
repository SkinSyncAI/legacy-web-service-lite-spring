package kr.gyk.voyageventures.beautyq.lite.web.service.component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieComponent {
    private final ScoringComponent scoringComponent;

    @Value("${server.domain}") private String serverDomain;

    public void initSession (HttpServletResponse response) {
        addCookie("scoring.random", String.valueOf(scoringComponent.getScoreError()), 1800, response);
    }

    public void setDiagnosisSkinType (DiagnosisTestForm diagnosisTestForm, HttpServletResponse response) {
        addCookie("diagnosis.test.q1", String.valueOf(diagnosisTestForm.getQuestion1() ? 1 : 0), 86400, response);
        addCookie("diagnosis.test.q2", String.valueOf(diagnosisTestForm.getQuestion2() ? 1 : 0), 86400, response);
        addCookie("diagnosis.test.q3", String.valueOf(diagnosisTestForm.getQuestion3() ? 1 : 0), 86400, response);
        addCookie("diagnosis.test.q4", String.valueOf(diagnosisTestForm.getQuestion4() ? 1 : 0), 86400, response);
    }

    public DiagnosisTestForm getDiagnosisSkinType (HttpServletRequest request) {
        DiagnosisTestForm diagnosisTestForm = new DiagnosisTestForm();
        diagnosisTestForm.setQuestion1(this.getCookie("diagnosis.test.q1", request).equals("1"));
        diagnosisTestForm.setQuestion2(this.getCookie("diagnosis.test.q2", request).equals("1"));
        diagnosisTestForm.setQuestion3(this.getCookie("diagnosis.test.q3", request).equals("1"));
        diagnosisTestForm.setQuestion4(this.getCookie("diagnosis.test.q4", request).equals("1"));

        return diagnosisTestForm;
    }

    public void addCookie (String key, String value, int age, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        //cookie.setDomain(serverDomain);
        response.addCookie(cookie);
    }

    public String getCookie (String key, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals(key))
                return cookie.getValue();
        return null;
    }

}
