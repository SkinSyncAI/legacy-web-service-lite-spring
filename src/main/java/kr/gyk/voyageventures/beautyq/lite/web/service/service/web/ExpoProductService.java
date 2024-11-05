package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpoProductService {
    private final CosmeticRepository cosmeticRepository;

    private final CosmeticService cosmeticService;

    public CosmeticDetailDTO getCosmeticDetailDTO (
            Long id
    ) throws Exception {
        return cosmeticService.getCosmeticDetail(id);
    }

    public short getCosmeticScore (
            Long id,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);
        CosmeticMatchingListElementDTO cosmeticMatchingListElementDTO = cosmeticService.getCosmeticMatchingListElement(cosmetic, diagnosisTestForm, mainTagForm, scoringRandomValue);
        return cosmeticMatchingListElementDTO.getScoreMatching();
    }

    public CosmeticListDTO getCosmeticListSimilarDTO (
            Long id,
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
        int i = 0;
        for (int j = 0; j < 6 && i < cosmeticMatchingListElementDTOList.size(); j++, i++) {
            if (Objects.equals(cosmeticMatchingListElementDTOList.get(i).getId(), id)) {
                i += 1;
                j -= 1;
                continue;
            }
            cosmeticList.add(cosmeticMatchingListElementDTOList.get(i).getId());
        }

        return cosmeticService.getCosmeticList(cosmeticList);
    }

    public CosmeticScoreListElementDTO getCosmeticScoreListDTOCurrent (
            Long id,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);
        CosmeticMatchingListElementDTO cosmeticMatchingListElementDTO = cosmeticService.getCosmeticMatchingListElement(cosmetic, diagnosisTestForm, mainTagForm, scoringRandomValue);

        return new CosmeticScoreListElementDTO(cosmetic, cosmeticMatchingListElementDTO.getScoreAll(), cosmeticMatchingListElementDTO.getScoreMatching());
    }

    public CosmeticScoreListDTO getCosmeticScoreListDTOSimilar (
            Long id,
            Short tag,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        List<CosmeticMatchingListElementDTO> cosmeticMatchingList = cosmeticService.getCosmeticMatchingListAll(diagnosisTestForm, mainTagForm, scoringRandomValue).getCosmeticList();
        cosmeticMatchingList.sort(new Comparator<CosmeticMatchingListElementDTO>() {
            @Override
            public int compare(CosmeticMatchingListElementDTO o1, CosmeticMatchingListElementDTO o2) {
                if (tag == 0) return o1.getScoreAll() - o2.getScoreAll();
                else if (tag == 1) return o1.getScoreHydration() - o2.getScoreHydration();
                else if (tag == 2) return o1.getScoreMoisture() - o2.getScoreMoisture();
                else if (tag == 3) return o1.getScoreBarrier() - o2.getScoreBarrier();
                else if (tag == 4) return o1.getScoreSoothing() - o2.getScoreSoothing();
                else if (tag == 5) return o1.getScoreBrightening() - o2.getScoreBrightening();
                return o1.getScoreMatching() - o2.getScoreMatching();
            }
        });

        List<CosmeticScoreListElementDTO> listCosmeticDTO = new ArrayList<>();

        int i = 0;
        for (int j = 0; j < 6 && i < cosmeticMatchingList.size(); j++, i++) {
            if (Objects.equals(cosmeticMatchingList.get(i).getId(), id)) {
                i += 1;
                j -= 1;
                continue;
            }
            Cosmetic cosmetic = cosmeticRepository.findById(cosmeticMatchingList.get(i).getId()).orElseThrow(EntityDataNotFoundException::new);

            short score = 0;
            if (tag == 0) score = cosmeticMatchingList.get(i).getScoreAll();
            else if (tag == 1) score = cosmeticMatchingList.get(i).getScoreHydration();
            else if (tag == 2) score = cosmeticMatchingList.get(i).getScoreMoisture();
            else if (tag == 3) score = cosmeticMatchingList.get(i).getScoreBarrier();
            else if (tag == 4) score = cosmeticMatchingList.get(i).getScoreSoothing();
            else if (tag == 5) score = cosmeticMatchingList.get(i).getScoreBrightening();

            listCosmeticDTO.add(new CosmeticScoreListElementDTO(cosmetic, score, cosmeticMatchingList.get(i).getScoreMatching()));
        }

        return CosmeticScoreListDTO.builder()
                .count((long) listCosmeticDTO.size())
                .cosmeticList(listCosmeticDTO)
                .build();
    }

}
