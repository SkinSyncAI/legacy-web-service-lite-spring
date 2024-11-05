package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpoDiagnosisResultDTO {
    private String baumannSkintype;
    private String skintype;
    private List<Integer> needs;
}
