package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpoMainTagListDTO {
    List<ExpoMainTagDTO> line1;
    List<ExpoMainTagDTO> line2;
}
