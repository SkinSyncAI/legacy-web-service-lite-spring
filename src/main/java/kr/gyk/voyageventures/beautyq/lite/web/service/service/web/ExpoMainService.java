package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticListDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticMatchingListElementDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpoMainService {
    private final CosmeticService cosmeticService;

    public CosmeticListDTO getExpoMainCosmeticListDTO (
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        List<CosmeticMatchingListElementDTO> cosmeticMatchingListElementDTOList = cosmeticService.getCosmeticMatchingListAll(diagnosisTestForm, mainTagForm, scoringRandomValue).getCosmeticList();
        cosmeticMatchingListElementDTOList.sort(new Comparator<CosmeticMatchingListElementDTO>() {
            @Override
            public int compare(CosmeticMatchingListElementDTO o1, CosmeticMatchingListElementDTO o2) {
                return o1.getScoreMatching() - o2.getScoreMatching();
            }
        });
        List<Long> cosmeticList = new ArrayList<>();
        for (int i = 0; i < 4 && i < cosmeticMatchingListElementDTOList.size(); i++) cosmeticList.add(cosmeticMatchingListElementDTOList.get(i).getId());

        return cosmeticService.getCosmeticList(cosmeticList);
    }

}