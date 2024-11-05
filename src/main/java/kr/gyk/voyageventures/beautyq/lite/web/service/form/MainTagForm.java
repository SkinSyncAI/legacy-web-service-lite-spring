package kr.gyk.voyageventures.beautyq.lite.web.service.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainTagForm {
    @NotNull
    private Boolean tag0;

    @NotNull
    private Boolean tag1;

    @NotNull
    private Boolean tag2;

    @NotNull
    private Boolean tag3;

    @NotNull
    private Boolean tag4;

    @NotNull
    private Boolean tag5;

    @NotNull
    private Boolean tag6;

    @NotNull
    private Boolean tag7;
}
