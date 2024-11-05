package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticScoreListElementDTO {
    private Long id;
    private String brand;
    private String name;
    private Integer priceDiscount;
    private String volume;
    private Double rating;
    private Integer countReview;
    private String image;
    private Short score;
    private Short matching;

    public CosmeticScoreListElementDTO (Cosmetic cosmetic, Short score, Short matching) {
        this.id = cosmetic.getId();
        this.brand = cosmetic.getBrand().getNameKo();
        this.name = cosmetic.getNameKo();
        this.priceDiscount = cosmetic.getPriceDiscount();
        this.volume = cosmetic.getVolume();
        this.rating = cosmetic.getRating();
        this.countReview = cosmetic.getCountReview();
        this.image = cosmetic.getImageProduct().getUrl();
        this.score = score;
        this.matching = matching;
    }
}
