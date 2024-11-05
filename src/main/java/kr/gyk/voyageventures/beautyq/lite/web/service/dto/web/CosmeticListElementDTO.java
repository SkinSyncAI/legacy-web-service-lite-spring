package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticListElementDTO {
    private Long id;
    private String brand;
    private String name;
    private Integer priceDiscount;
    private String volume;
    private Double rating;
    private Integer countReview;
    private String image;

    public CosmeticListElementDTO (Cosmetic cosmetic) {
        this.id = cosmetic.getId();
        this.brand = cosmetic.getBrand().getNameKo();
        this.name = cosmetic.getNameKo();
        this.priceDiscount = cosmetic.getPriceDiscount();
        this.volume = cosmetic.getVolume();
        this.rating = cosmetic.getRating();
        this.countReview = cosmetic.getCountReview();
        this.image = cosmetic.getImageProduct().getUrl();
    }
}
