package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCosmeticResultDTO {
    private Long id;
    private String name;
    private String image;
    private Short score;
    private Short scoreDelta;
}
