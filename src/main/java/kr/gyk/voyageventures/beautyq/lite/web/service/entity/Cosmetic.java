package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.utils.StringListConverter;
import lombok.*;
import org.codehaus.groovy.vmplugin.v8.CacheableCallSite;

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

}
