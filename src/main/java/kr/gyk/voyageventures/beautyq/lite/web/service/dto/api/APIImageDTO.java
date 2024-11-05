package kr.gyk.voyageventures.beautyq.lite.web.service.dto.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Image;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIImageDTO {
    private Long id;
    private String url;

    public APIImageDTO (Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
