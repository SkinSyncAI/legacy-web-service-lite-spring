package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cosmetic_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 64)
    private String nameKo;

    @Column(nullable = false, unique = false, length = 64)
    private String nameEn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Cosmetic> cosmetic = new ArrayList<>();

}
