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
                return o1.getScoreMatching() - o2.getScoreMatching();
            }
        });

        if (source.getId().longValue() == listDTO.getFirst().getId().longValue())
            return EventCosmeticResultDTO.builder()
                    .id(id)
                    .name(cosmetic.getNameKo())
                    .image(cosmetic.getImageProduct().getUrl())
                    .score(listDTO.getFirst().getScoreMatching())
                    .scoreDelta((short) 0)
                    .build();

        long low = 0, high = listDTO.size() - 1, mid = 1;
        while (low <= high) {
            mid = (high - low) / 2;
            if (listDTO.get((int) mid).getScoreMatching() < listDTO.get(Math.toIntExact(source.getId())).getScoreMatching()) high = mid - 1;
            else if (listDTO.get((int) mid).getScoreMatching() > listDTO.get(Math.toIntExact(source.getId())).getScoreMatching()) low = mid + 1;
            else break;
        }

        Cosmetic cosmeticResult = cosmeticRepository.findById(listDTO.get((int) (mid - 1)).getId()).orElseThrow(EntityDataNotFoundException::new);

        return EventCosmeticResultDTO.builder()
                .id(listDTO.get((int) (mid - 1)).getId())
                .name(cosmeticResult.getNameKo())
                .image(cosmeticResult.getImageProduct().getUrl())
                .score(listDTO.get((int) (mid - 1)).getScoreMatching())
                .scoreDelta((short) (listDTO.get((int) (mid - 1)).getScoreMatching() - listDTO.get((int) mid).getScoreMatching()))
                .build();
    }
}
