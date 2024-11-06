package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticScoreListDTO {
    private Long count;
    private List<CosmeticScoreListElementDTO> cosmeticList = new ArrayList<>();
}
