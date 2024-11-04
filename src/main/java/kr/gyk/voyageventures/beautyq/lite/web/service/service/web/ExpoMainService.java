package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticListDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoMainTagDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoMainTagListDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpoMainService {
    private final CosmeticService cosmeticService;

    public CosmeticListDTO getExpoMainCosmeticListDTO (
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        // TODO
        List<Long> cosmeticList = new ArrayList<>();
        cosmeticList.add(3L);

        cosmeticService.getCosmeticMatchingListAll(diagnosisTestForm, mainTagForm, scoringRandomValue);

        return cosmeticService.getCosmeticList(cosmeticList);
    }

}
