package kr.gyk.voyageventures.beautyq.lite.web.service.entity;

import jakarta.persistence.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.StringListConverter;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = true, unique = false, length = 512)
    private List<String> nameKo;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = true, unique = false, length = 512)
    private List<String> nameEn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Cosmetic> cosmetic = new ArrayList<>();

}
