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
    private CosmeticBrand cosmeticBrand;

    @Column(nullable = false, unique = false, length = 256)
    private Long nameKo;

    @Column(nullable = false, unique = false, length = 256)
    private Long nameEn;

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

    @Column(nullable = false, unique = false)
    private Double rating;

    @Column(nullable = false, unique = false)
    private Integer countReview;

    @Column(nullable = false, unique = false)
    private Integer cost;

    @Column(nullable = false, unique = false)
    private Integer discount;

}
