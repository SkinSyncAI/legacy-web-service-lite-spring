package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticMatchingListElementDTO {
    private Long id;
    private String name;

    private Short scoreMatching;

    private Short scoreAll;
    private Short scoreHydration;
    private Short scoreSoothing;
    private Short scoreBrightening;
    private Short scoreBarrier;
    private Short scoreMoisture;
}
