package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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
    private Brand brand;

    @Column(nullable = false, unique = false, length = 256)
    private Long nameKo;

    @Column(nullable = false, unique = false, length = 256)
    private Long nameEn;

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY)
    private List<Category> category = new ArrayList<>();

    @ManyToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY)
    private List<Ingredient> ingredient = new ArrayList<>();

}
