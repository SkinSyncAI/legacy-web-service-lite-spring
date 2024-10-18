package kr.gyk.voyageventures.beautyq.lite.web.service.api.dto;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticCategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APICosmeticCategoryInfoDTO {
    private Integer id;
    private String code;
    private String nameKo;
    private String nameEn;

    public APICosmeticCategoryInfoDTO (CosmeticCategory cosmeticCategory) {
        this.id = cosmeticCategory.getId();
        this.code = cosmeticCategory.getCode();
        this.nameKo = cosmeticCategory.getNameKo();
        this.nameEn = cosmeticCategory.getNameEn();
    }
}
