package kr.gyk.voyageventures.beautyq.lite.web.service.dto.api;

import lombok.*;

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

    private String image;
    private List<String> tag;
    private Double rating;
    private Integer countReview;
    private Integer cost;
    private Integer discount;
    private String howToUse;
    private Integer recommendSkin;

    private Integer scoreHydration;
    private Integer scoreSoothing;
    private Integer scoreBrightening;
    private Integer scoreBarrier;
    private Integer scoreMoisture;

    private Integer typeScoreD;
    private Integer typeScoreO;
    private Integer typeScoreS;
    private Integer typeScoreR;
    private Integer typeScoreP;
    private Integer typeScoreN;
    private Integer typeScoreW;
    private Integer typeScoreT;
}
