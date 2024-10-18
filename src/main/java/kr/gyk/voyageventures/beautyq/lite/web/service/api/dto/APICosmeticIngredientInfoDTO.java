package kr.gyk.voyageventures.beautyq.lite.web.service.api.dto;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticIngredient;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APICosmeticIngredientInfoDTO {
    private Integer id;
    private String code;
    private List<String> nameKo;
    private List<String> nameEn;

    public APICosmeticIngredientInfoDTO (CosmeticIngredient cosmeticIngredient) {
        this.id = cosmeticIngredient.getId();
        this.code = cosmeticIngredient.getCode();
        this.nameKo = cosmeticIngredient.getNameKo();
        this.nameEn = cosmeticIngredient.getNameEn();
    }
}
