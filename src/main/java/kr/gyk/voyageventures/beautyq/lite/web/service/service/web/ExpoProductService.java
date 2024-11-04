package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpoProductService {
    private final CosmeticRepository cosmeticRepository;
    private final CosmeticService cosmeticService;

    public CosmeticDetailDTO getCosmeticDetailDTO (Long id) throws Exception {
        return cosmeticService.getCosmeticDetail(id);
    }

    public CosmeticListDTO getCosmeticListSimilarDTO (
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        // TODO
        List<Long> cosmeticList = new ArrayList<>();
        cosmeticList.add(3L);

        return cosmeticService.getCosmeticList(cosmeticList);
    }

    public CosmeticScoreListElementDTO getCosmeticScoreListDTOCurrent (
            Long id,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(DataFormatException::new);

        // TODO
        short score = 99;
        short matching = 99;

        return new CosmeticScoreListElementDTO(cosmetic, score, matching);
    }

    public CosmeticScoreListDTO getCosmeticScoreListDTOSimilar (
            Long id,
            Short tag,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        // TODO
        List<Long> listCosmetic = new ArrayList<>();
        listCosmetic.add(3L);
        listCosmetic.add(3L);

        List<CosmeticScoreListElementDTO> listCosmeticDTO = new ArrayList<>();
        for (Long i : listCosmetic) listCosmeticDTO.add(this.getCosmeticScoreListDTOCurrent(i, diagnosisTestForm, mainTagForm, scoringRandomValue));

        return CosmeticScoreListDTO.builder()
                .count((long) listCosmeticDTO.size())
                .cosmeticList(listCosmeticDTO)
                .build();
    }

}
