package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticListDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticListElementDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoSearchProductDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public CosmeticListDTO getExpoSearchTextAjax (
            String keyword
    ) throws Exception {
        if (keyword == null || keyword.isEmpty())
            return CosmeticListDTO.builder().count((long) 0).cosmeticList(new ArrayList<>()).build();

        List<CosmeticListElementDTO> listCosmeticListDTO_match = new ArrayList<>();
        List<CosmeticListElementDTO> listCosmeticListDTO_contain = new ArrayList<>();
        List<CosmeticListElementDTO> listCosmeticListDTO_splitContain = new ArrayList<>();

        String [] keywordSplit = keyword.split(" ");

        List<Cosmetic> listCosmetic = cosmeticRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        for (Cosmetic cosmetic : listCosmetic) {
            if ( cosmetic.getNameKo().equals(keyword) || cosmetic.getBrand().getNameKo().equals(keyword)){
                listCosmeticListDTO_match.addFirst(new CosmeticListElementDTO(cosmetic));
                continue;
            }
            if ( cosmetic.getNameKo().contains(keyword) || cosmetic.getBrand().getNameKo().contains(keyword)) {
                listCosmeticListDTO_contain.add(new CosmeticListElementDTO(cosmetic));
                continue;
            }
            for (String key : keywordSplit)
                if (cosmetic.getNameKo().contains(key)) {
                    listCosmeticListDTO_splitContain.add(new CosmeticListElementDTO(cosmetic));
                    continue;
                }
        }

        listCosmeticListDTO_match.addAll(listCosmeticListDTO_contain);
        listCosmeticListDTO_match.addAll(listCosmeticListDTO_splitContain);


        return CosmeticListDTO.builder()
                .count((long) listCosmeticListDTO_match.size())
                .cosmeticList(listCosmeticListDTO_match)
                .build();
    }

    private String[][] getIndexing() {
        String[][] result = {{"레드 블레미쉬 클리어 수딩 액티브 에센스", "닥터지"},
                {"블랙 스네일 레티놀 앰플", "닥터지"},
                {"블랙 스네일 크림", "닥터지"},
                {"블랙스네일 에멀젼", "닥터지"},
                {"블랙스네일 토너", "닥터지"},
                {"화이트 트러플 수프림 인텐시브 세럼", "달바"},
                {"똘러리앙 덤알레르고 플루이드", "라로슈포제"},
                {"1025 독도 토너", "라운드랩"},
                {"1025 독도 필링젤", "라운드랩"},
                {"어드밴스드 제니피끄 세럼", "랑콤"},
                {"엑스트라 슈퍼9 플러스 모공 타이트닝 앰플", "메디필"},
                {"티트리 케어 솔루션 에센셜 마스크 REX", "메디힐"},
                {"리들샷 100 페이셜 부스팅 퍼스트 앰플", "브이티코스메틱"},
                {"엔젤아쿠아 데일리 시카 크림", "비욘드"},
                {"뉴메로 10 에센스", "빌리프 "},
                {"자음수 ", "설화수"},
                {"로열허니 프로폴리스 인리치 에센스", "스킨푸드"},
                {"AHA BHA PHA 30 데이즈 미라클 토너", "썸바이비"},
                {"CPI 스킨 리커버리 크림", "아벤느"},
                {"아크니 닥터 1st 컨트롤 토닉", "아이소이"},
                {"아크니 닥터 1st 컨트롤 세럼", "아이소이"},
                {"아토베리어 365 하이드로 에센스", "에스트라"},
                {"어드밴스드 나이트 리페어", "에스티로더"},
                {"그린티 씨드 히알루론산 세럼", "이니스프리"},
                {"비자 시카밤", "이니스프리 "},
                {"다이브인 저분자 히알루론산 토너", "토리든"},
                {"다이브인 저분자 히알루론산 세럼", "토리든"},
                {"리얼 히아룰로닉 블루 100 앰플", "웰라쥬"},
                {"그린토마토 포어 리프팅 앰플 플러스", "성분에디터"},
                {"리들샷 100", "브이티코스메틱"},
                {"레티놀 시카 흔적 앰플", "이니스프리"},
                {"비피다 바이옴 앰플 토너", "마녀공장"},
                {"윤조에센스", "설화수"},
                {"캐모마일 약산성 토너", "비플레인"},
                {"아토베리어 365 로션", "에스트라"},
                {"더 6 펩타이드 스킨 부스터 세럼", "코스알엑스"},
                {"아하바하 토너", "코스알엑스"},
                {"풀핏 프로폴리스 라이트 앰플", "코스알엑스"},
                {"아쿠아 오아시스 토너 ", "에스네이처"},
                {"화이트 글로우 앰플", "에스네이처"},
                {"어성초 77 수딩 토너", "아누아"},
                {"익스트림 리베리어 에센셜 로션", "에이솔루션"},
                {"아쿠아 스쿠알란 세럼", "에스네이처"},
                {"맑은쌀채운토너", "조선미녀"},
                {"판테토인 에센스 토너", "마녀공장"},
                {"수딩 젤 로션", "아토팜"},
                {"마데카소사이드 흔적 리페어 세럼", "메디힐"},
                {"녹두 모공 타이트업 수딩 크림", "비플레인"},
                {"블랙티 유스 인핸싱 앰플", "이니스프리"},
                {"올리고 히알루론산 딥 토너", "더랩바이블랑두"}};
        return result;
    }

}
