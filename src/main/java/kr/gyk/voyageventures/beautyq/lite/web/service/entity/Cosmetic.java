package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.utils.StringListConverter;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cosmetic")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cosmetic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private CosmeticBrand brand;

    @Column(nullable = false, unique = false, length = 512)
    private String nameKo;

    @Column(nullable = true, unique = false, length = 256)
    private String nameEn;

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<CosmeticCategory> category = new ArrayList<>();

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<CosmeticIngredient> ingredient = new ArrayList<>();

    @Column(nullable = true, unique = false)
    private Integer price;

    @Column(nullable = true, unique = false)
    private Integer priceDiscount;

    @Column(nullable = true, unique = false)
    private Integer shippingFee;

    @Column(nullable = true, unique = false)
    private String volume;

    @Column(nullable = true, unique = false)
    private Double rating;

    @Column(nullable = true, unique = false)
    private Integer countReview;

    @Column(nullable = true, unique = false)
    private Integer countPurchase;

    @OneToOne
    private Image imageProduct;

    @OneToMany
    private List<Image> imageThumbnails = new ArrayList<>();

    @Column(nullable = true, unique = false)
    private Integer typeScoreD;

    @Column(nullable = true, unique = false)
    private Integer typeScoreO;

    @Column(nullable = true, unique = false)
    private Integer typeScoreS;

    @Column(nullable = true, unique = false)
    private Integer typeScoreR;

    @Column(nullable = true, unique = false)
    private Integer typeScoreP;

    @Column(nullable = true, unique = false)
    private Integer typeScoreN;

    @Column(nullable = true, unique = false)
    private Integer typeScoreW;

    @Column(nullable = true, unique = false)
    private Integer typeScoreT;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = true, unique = false, length = 512)
    private List<String> keyword;

    @Column(nullable = true, unique = false)
    private Integer scoreHydration;

    @Column(nullable = true, unique = false)
    private Integer scoreSoothing;

    @Column(nullable = true, unique = false)
    private Integer scoreBrightening;

    @Column(nullable = true, unique = false)
    private Integer scoreBarrier;

    @Column(nullable = true, unique = false)
    private Integer scoreMoisture;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = true, unique = false, length = 512)
    private List<String> ingredientKeyword;

    @Column(nullable = true, unique = false)
    private Short ingredientMatchingGood;

    @Column(nullable = true, unique = false)
    private Short ingredientMatchingBad;

    @Column(nullable = true, unique = false)
    private Short ingredientProhibit;

}
