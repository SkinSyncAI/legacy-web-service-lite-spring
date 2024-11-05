package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticCompareAjaxGraphDTO {
    private Long id;
    private String name;
    private Short score;
}
