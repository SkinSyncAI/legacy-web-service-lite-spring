package kr.gyk.voyageventures.beautyq.lite.web.service.dto.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Image;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticDetailDTO {
    private Long id;
    private String brand;
    private String name;
    private Integer price;
    private Integer priceDiscount;
    private Integer shippingFee;
    private Double rating;
    private Integer countReview;
    private Integer countPurchase;
    private List<String> imageThumbnails = new ArrayList<>();
    private Short ingredientMatchingGood;
    private Short ingredientMatchingBad;

    public CosmeticDetailDTO (Cosmetic cosmetic) {
        this.id = cosmetic.getId();
        this.brand = cosmetic.getBrand().getNameKo();
        this.name = cosmetic.getNameKo();
        this.price = cosmetic.getPrice();
        this.priceDiscount = cosmetic.getPriceDiscount();
        this.shippingFee = cosmetic.getShippingFee();
        this.rating = cosmetic.getRating();
        this.countReview = cosmetic.getCountReview();
        this.countPurchase = cosmetic.getCountPurchase();
        for (Image image : cosmetic.getImageThumbnails()) imageThumbnails.add(image.getUrl());
        this.ingredientMatchingGood = cosmetic.getIngredientMatchingGood();
        this.ingredientMatchingBad = cosmetic.getIngredientMatchingBad();
    }
}
