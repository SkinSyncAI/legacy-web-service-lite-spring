package kr.gyk.voyageventures.beautyq.lite.web.service.dto.api;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIPostCosmeticDTO {
    private Integer brand; // 브랜드
    private String nameKo; // 이름
    private String nameEn; // 영어명 X
    private List<Integer> category; // 카테고리 X
    private List<Integer> ingredient; // 전성분 X

    private Integer price; // 정가
    private Integer priceDiscount; // 할인가
    private Integer shippingFee; // 배송료 3000원?
    private String volume; // 용량 (단위까지)
    private Double rating; // 별점 (5.0 max)
    private Integer countReview; // 리뷰수
    private Integer countPurchase; // 구매수

    private String imageProduct; // 제품 사진 (리스트용)
    private List<String> imageThumbnails = new ArrayList<>(); // 제품 썸네일 사진 (상세용)

    // 바우만 타입 점수
    private Integer typeScoreD;
    private Integer typeScoreO;
    private Integer typeScoreS;
    private Integer typeScoreR;
    private Integer typeScoreP;
    private Integer typeScoreN;
    private Integer typeScoreW;
    private Integer typeScoreT;
    private List<String> keyword; // 리뷰 키워드

    // 성분 점수
    private Integer scoreHydration;
    private Integer scoreSoothing;
    private Integer scoreBrightening;
    private Integer scoreBarrier;
    private Integer scoreMoisture;
    private List<String> ingredientKeyword; // 성분 키워드
    private Short ingredientMatchingGood; // 성분 매칭 Good 항목 수
    private Short ingredientMatchingBad; // 성분 매칭 Bad 항목 수
    private Short ingredientProhibit; // 금지/제한 성분 수
}
