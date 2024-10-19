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

    @JoinColumn(name = "brand")
    @ManyToOne(fetch = FetchType.LAZY)
    private CosmeticBrand brand;

    @Column(nullable = false, unique = false, length = 256)
    private String nameKo;

    @Column(nullable = false, unique = false, length = 256)
    private String nameEn;

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CosmeticCategory> category = new ArrayList<>();

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CosmeticIngredient> ingredient = new ArrayList<>();

    // TODO: Analysis

    @Column(nullable = true, unique = false, length = 2048)
    private String image;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = true, unique = false, length = 512)
    private List<String> tag;

    @Column(nullable = true, unique = false)
    private Double rating;

    @Column(nullable = true, unique = false)
    private Integer countReview;

    @Column(nullable = true, unique = false)
    private Integer cost;

    @Column(nullable = true, unique = false)
    private Integer discount;

    @Column(nullable = true, unique = false, length = 768)
    private String howToUse;

    @Column(nullable = true, unique = false)
    private Integer recommendSkin;

}
