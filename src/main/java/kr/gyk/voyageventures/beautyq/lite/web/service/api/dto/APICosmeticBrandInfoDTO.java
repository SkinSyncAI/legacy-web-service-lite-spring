package kr.gyk.voyageventures.beautyq.lite.web.service.api.dto;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticBrand;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APICosmeticBrandInfoDTO {
    private Integer id;
    private String code;
    private String nameKo;
    private String nameEn;

    public APICosmeticBrandInfoDTO (CosmeticBrand cosmeticBrand) {
        this.id = cosmeticBrand.getId();
        this.code = cosmeticBrand.getCode();
        this.nameKo = cosmeticBrand.getNameKo();
        this.nameEn = cosmeticBrand.getNameEn();
    }
}
