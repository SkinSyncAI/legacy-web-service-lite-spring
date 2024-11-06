package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.component.ScoringComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CosmeticService {
    private final CosmeticRepository cosmeticRepository;

    private final ScoringComponent scoringComponent;

    public CosmeticDetailDTO getCosmeticDetail (Long id) throws Exception {
        return new CosmeticDetailDTO(cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
    }

    public CosmeticListDTO getCosmeticList (List<Long> idList) throws Exception {
        List<CosmeticListElementDTO> listElement = new ArrayList<>();
        for (Long id : idList) listElement.add(this.getCosmeticListElement(id));

        return CosmeticListDTO.builder()
                .cosmeticList(listElement)
                .count((long) listElement.size())
                .build();
    }

    public CosmeticListElementDTO getCosmeticListElement (Long id) throws Exception {
        return new CosmeticListElementDTO(cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
    }

    public CosmeticMatchingListDTO getCosmeticMatchingListAll (
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        List<Cosmetic> cosmeticList = cosmeticRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        int [][] stUser = {{25, 25, 25, 25}, {25, 25, 25, 25}};
        stUser[diagnosisTestForm.getQuestion1() ? 0 : 1][0] += 50;
        stUser[diagnosisTestForm.getQuestion2() ? 0 : 1][1] += 50;
        stUser[diagnosisTestForm.getQuestion3() ? 0 : 1][2] += 50;
        stUser[diagnosisTestForm.getQuestion4() ? 0 : 1][3] += 50;

        Set<String> tagUser = new HashSet<>();
        ExpoMainTagListDTO tagList = this.getExpoMainTagListDTO();
        if (mainTagForm.getTag0()) tagUser.add(tagList.getLine1().get(0).getName());
        if (mainTagForm.getTag1()) tagUser.add(tagList.getLine1().get(1).getName());
        if (mainTagForm.getTag2()) tagUser.add(tagList.getLine1().get(2).getName());
        if (mainTagForm.getTag3()) tagUser.add(tagList.getLine1().get(3).getName());
        if (mainTagForm.getTag4()) tagUser.add(tagList.getLine2().get(0).getName());
        if (mainTagForm.getTag5()) tagUser.add(tagList.getLine2().get(1).getName());
        if (mainTagForm.getTag6()) tagUser.add(tagList.getLine2().get(2).getName());
        if (mainTagForm.getTag7()) tagUser.add(tagList.getLine2().get(3).getName());

        int scoreError = scoringRandomValue;

        List<CosmeticMatchingListElementDTO> listDTO = new ArrayList<>();
        for (Cosmetic cosmetic : cosmeticList) listDTO.add(this.getCosmeticMatchingListElement(cosmetic, stUser, tagUser, scoreError));

        return CosmeticMatchingListDTO.builder()
                .count((long) listDTO.size())
                .cosmeticList(listDTO)
                .build();
    }

    public CosmeticMatchingListDTO getCosmeticMatchingListAll (
            List<Long> cosmeticIdList,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        List<Cosmetic> cosmeticList = new ArrayList<>();
        for (Long id : cosmeticIdList) cosmeticList.add(cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));

        int [][] stUser = {{25, 25, 25, 25}, {25, 25, 25, 25}};
        stUser[diagnosisTestForm.getQuestion1() ? 0 : 1][0] += 50;
        stUser[diagnosisTestForm.getQuestion2() ? 0 : 1][1] += 50;
        stUser[diagnosisTestForm.getQuestion3() ? 0 : 1][2] += 50;
        stUser[diagnosisTestForm.getQuestion4() ? 0 : 1][3] += 50;

        Set<String> tagUser = new HashSet<>();
        ExpoMainTagListDTO tagList = this.getExpoMainTagListDTO();
        if (mainTagForm.getTag0()) tagUser.add(tagList.getLine1().get(0).getName());
        if (mainTagForm.getTag1()) tagUser.add(tagList.getLine1().get(1).getName());
        if (mainTagForm.getTag2()) tagUser.add(tagList.getLine1().get(2).getName());
        if (mainTagForm.getTag3()) tagUser.add(tagList.getLine1().get(3).getName());
        if (mainTagForm.getTag4()) tagUser.add(tagList.getLine2().get(0).getName());
        if (mainTagForm.getTag5()) tagUser.add(tagList.getLine2().get(1).getName());
        if (mainTagForm.getTag6()) tagUser.add(tagList.getLine2().get(2).getName());
        if (mainTagForm.getTag7()) tagUser.add(tagList.getLine2().get(3).getName());

        int scoreError = scoringRandomValue;

        List<CosmeticMatchingListElementDTO> listDTO = new ArrayList<>();
        for (Cosmetic cosmetic : cosmeticList) listDTO.add(this.getCosmeticMatchingListElement(cosmetic, stUser, tagUser, scoreError));

        return CosmeticMatchingListDTO.builder()
                .count((long) listDTO.size())
                .cosmeticList(listDTO)
                .build();
    }

    public CosmeticMatchingListElementDTO getCosmeticMatchingListElement (
            Cosmetic cosmetic,
            DiagnosisTestForm diagnosisTestForm, MainTagForm mainTagForm, Integer scoringRandomValue
    ) throws Exception {
        int [][] stUser = {{25, 25, 25, 25}, {25, 25, 25, 25}};
        stUser[diagnosisTestForm.getQuestion1() ? 0 : 1][0] += 50;
        stUser[diagnosisTestForm.getQuestion2() ? 0 : 1][1] += 50;
        stUser[diagnosisTestForm.getQuestion3() ? 0 : 1][2] += 50;
        stUser[diagnosisTestForm.getQuestion4() ? 0 : 1][3] += 50;

        Set<String> tagUser = new HashSet<>();
        ExpoMainTagListDTO tagList = this.getExpoMainTagListDTO();
        if (mainTagForm.getTag0()) tagUser.add(tagList.getLine1().get(0).getName());
        if (mainTagForm.getTag1()) tagUser.add(tagList.getLine1().get(1).getName());
        if (mainTagForm.getTag2()) tagUser.add(tagList.getLine1().get(2).getName());
        if (mainTagForm.getTag3()) tagUser.add(tagList.getLine1().get(3).getName());
        if (mainTagForm.getTag4()) tagUser.add(tagList.getLine2().get(0).getName());
        if (mainTagForm.getTag5()) tagUser.add(tagList.getLine2().get(1).getName());
        if (mainTagForm.getTag6()) tagUser.add(tagList.getLine2().get(2).getName());
        if (mainTagForm.getTag7()) tagUser.add(tagList.getLine2().get(3).getName());

        int scoreError = scoringRandomValue;

        return this.getCosmeticMatchingListElement(cosmetic, stUser, tagUser, scoreError);
    }

    public CosmeticMatchingListElementDTO getCosmeticMatchingListElement (
            Cosmetic cosmetic,
            int [][] stUser, Set<String> tagUser, int scoreError
    ) throws Exception {
        int [][] stCosmetic = {
                {cosmetic.getTypeScoreD(), cosmetic.getTypeScoreS(), cosmetic.getTypeScoreP(), cosmetic.getTypeScoreW()},
                {cosmetic.getTypeScoreO(), cosmetic.getTypeScoreR(), cosmetic.getTypeScoreN(), cosmetic.getTypeScoreT()}
        };
        Set<String> tagCosmetic = new HashSet<>(cosmetic.getKeyword());
        int countProhibit = cosmetic.getIngredientProhibit();

        int score = scoringComponent.getMatchingScore(stCosmetic, stUser, tagCosmetic, tagUser, countProhibit, scoreError);

        short scoreHydration = (short) ((short) score * 0.6 + cosmetic.getScoreHydration() * 0.4);
        short scoreSoothing = (short) ((short) score * 0.6 + cosmetic.getScoreSoothing() * 0.4);
        short scoreBrightening = (short) ((short) score * 0.6 + cosmetic.getScoreBrightening() * 0.4);
        short scoreBarrier = (short) ((short) score * 0.6 + cosmetic.getScoreBarrier() * 0.4);
        short scoreMoisture = (short) ((short) score * 0.6 + cosmetic.getScoreMoisture() * 0.4);
        short scoreAll = (short) ((scoreHydration + scoreSoothing + scoreBrightening + scoreBarrier + scoreMoisture) / 5);

        return CosmeticMatchingListElementDTO.builder()
                .id(cosmetic.getId())
                .name(cosmetic.getNameKo())
                .scoreMatching((short) score)
                .scoreAll(scoreAll)
                .scoreHydration(scoreHydration)
                .scoreSoothing(scoreSoothing)
                .scoreBrightening(scoreBrightening)
                .scoreBarrier(scoreBarrier)
                .scoreMoisture(scoreMoisture)
                .build();
    }

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
