package kr.gyk.voyageventures.beautyq.lite.web.service.dto.api;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIGetCosmeticDTO {
    private Long id;

    private APICosmeticBrandInfoDTO brand;
    private String nameKo;
    private String nameEn;
    private List<APICosmeticCategoryInfoDTO> category;
    private List<APICosmeticIngredientInfoDTO> ingredient;

    private Integer price;
    private Integer priceDiscount;
    private Integer shippingFee;
    private String volume;
    private Double rating;
    private Integer countReview;
    private Integer countPurchase;

    private APIImageDTO imageProduct;
    private List<APIImageDTO> imageThumbnails = new ArrayList<>();

    private Integer typeScoreD;
    private Integer typeScoreO;
    private Integer typeScoreS;
    private Integer typeScoreR;
    private Integer typeScoreP;
    private Integer typeScoreN;
    private Integer typeScoreW;
    private Integer typeScoreT;
    private List<String> keyword;

    private Integer scoreHydration;
    private Integer scoreSoothing;
    private Integer scoreBrightening;
    private Integer scoreBarrier;
    private Integer scoreMoisture;
    private List<String> ingredientKeyword;
    private Short ingredientMatchingGood;
    private Short ingredientMatchingBad;
    private Short ingredientProhibit;
}
