package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoMainTagDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoMainTagListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpoMainService {

    public ExpoMainTagListDTO getExpoMainTagListDTO () {
        List<ExpoMainTagDTO> tagLine1 = new ArrayList<>();
        List<ExpoMainTagDTO> tagLine2 = new ArrayList<>();

        tagLine1.add(ExpoMainTagDTO.builder().id(0).name("주름개선").build());
        tagLine1.add(ExpoMainTagDTO.builder().id(1).name("모공제거").build());
        tagLine1.add(ExpoMainTagDTO.builder().id(2).name("수분보충").build());
        tagLine1.add(ExpoMainTagDTO.builder().id(3).name("미백").build());

        tagLine2.add(ExpoMainTagDTO.builder().id(4).name("시카앰플").build());
        tagLine2.add(ExpoMainTagDTO.builder().id(5).name("트러블진정").build());
        tagLine2.add(ExpoMainTagDTO.builder().id(6).name("피부장벽").build());
        tagLine2.add(ExpoMainTagDTO.builder().id(7).name("수분보호").build());

        return ExpoMainTagListDTO.builder()
                .line1(tagLine1)
                .line2(tagLine2)
                .build();
    }

}
