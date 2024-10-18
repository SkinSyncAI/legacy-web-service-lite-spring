package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cosmetic_brand")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(nullable = true, unique = false, length = 128)
    private String nameKo;

    @Column(nullable = true, unique = false, length = 128)
    private String nameEn;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Cosmetic> cosmetic = new ArrayList<>();

}
