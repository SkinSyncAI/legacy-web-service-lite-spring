package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpoSearchProductDTO {
    private Long id;

    private String image;
    private String brand;
    private String name;
    private Short score;

    private Integer scoreHydration;
    private Integer scoreSoothing;
    private Integer scoreBrightening;
    private Integer scoreBarrier;
    private Integer scoreMoisture;
    private List<String> keyword = new ArrayList<>();

    private String descKeyword;
    private String descLine;
}
