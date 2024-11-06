package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticMatchingListDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticMatchingListElementDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.EventCosmeticResultDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpoEventService {
    private final CosmeticRepository cosmeticRepository;

    private final CosmeticService cosmeticService;

    public EventCosmeticResultDTO getExpoEventResult (
            Long id,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);
        CosmeticMatchingListElementDTO source = cosmeticService.getCosmeticMatchingListElement(cosmetic, diagnosisTestForm, mainTagForm, scoringRandomValue);

        List<CosmeticMatchingListElementDTO> listDTO = cosmeticService.getCosmeticMatchingListAll(diagnosisTestForm, mainTagForm, scoringRandomValue).getCosmeticList();
        listDTO.sort(new Comparator<CosmeticMatchingListElementDTO>() {
            @Override
            public int compare(CosmeticMatchingListElementDTO o1, CosmeticMatchingListElementDTO o2) {
                return o2.getScoreMatching() - o1.getScoreMatching();
            }
        });

        long mid;
        for (mid = 0; mid < listDTO.size(); mid++) if (id == listDTO.get((int) mid).getId().longValue()) break;

        long delta = mid - 1;
        if (delta < 0) delta = 0;
        for (; delta >= 0; delta--) if (source.getScoreMatching().longValue() < listDTO.get((int) delta).getScoreMatching().longValue()) break;

        if (delta < 0){
            Cosmetic cosmeticResult = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);
            return EventCosmeticResultDTO.builder()
                    .id(id)
                    .name(cosmeticResult.getNameKo())
                    .image(cosmeticResult.getImageProduct().getUrl())
                    .score(listDTO.get((int) mid).getScoreMatching())
                    .scoreDelta((short) 0)
                    .build();
        }

        Cosmetic cosmeticResult = cosmeticRepository.findById(listDTO.get((int) delta).getId()).orElseThrow(EntityDataNotFoundException::new);

        return EventCosmeticResultDTO.builder()
                .id(listDTO.get((int) delta).getId())
                .name(cosmeticResult.getNameKo())
                .image(cosmeticResult.getImageProduct().getUrl())
                .score(listDTO.get((int) delta).getScoreMatching())
                .scoreDelta((short) (listDTO.get((int) delta).getScoreMatching() - listDTO.get((int) mid).getScoreMatching()))
                .build();
    }
}
