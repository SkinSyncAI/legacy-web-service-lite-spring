package kr.gyk.voyageventures.beautyq.lite.web.service.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisTestForm {
    @NotNull
    private Boolean question1;

    @NotNull
    private Boolean question2;

    @NotNull
    private Boolean question3;

    @NotNull
    private Boolean question4;
}
