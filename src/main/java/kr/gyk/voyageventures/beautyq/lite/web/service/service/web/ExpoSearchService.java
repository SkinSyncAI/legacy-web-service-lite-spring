package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoSearchProductDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpoSearchService {
    private final CosmeticRepository cosmeticRepository;

    private final CosmeticService cosmeticService;

    public ExpoSearchProductDTO getSearchProductDTO (
            Long id,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        return ExpoSearchProductDTO.builder()
                .id(id)
                .image(cosmetic.getImageProduct().getUrl())
                .brand(cosmetic.getBrand().getNameKo())
                .name(cosmetic.getNameKo())
                .score(cosmeticService.getCosmeticMatchingListElement(cosmetic, diagnosisTestForm, mainTagForm, scoringRandomValue).getScoreMatching())
                .scoreHydration(cosmetic.getScoreHydration())
                .scoreSoothing(cosmetic.getScoreSoothing())
                .scoreBrightening(cosmetic.getScoreBrightening())
                .scoreBarrier(cosmetic.getScoreBarrier())
                .scoreMoisture(cosmetic.getScoreMoisture())
                .keyword(cosmetic.getIngredientKeyword())
                .descKeyword("속건조 보습 수분크림")
                .descLine("수분힐링 촉촉 피부 기대할 수 있어요.")
                .build();
    }

}
