package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticCompareAjaxJsonRequestDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.CosmeticCompareAjaxJsonResponseDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expo/product")
public class ExpoProductRestController {
    private final CookieComponent cookieComponent;
    private final ExpoProductService expoProductService;

    @GetMapping("/{id}/compare/offcanvas/ajax/json")
    public ResponseEntity<CosmeticCompareAjaxJsonResponseDTO> getExpoProductCompareOffcanvasAjaxJson (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable("id") Long id,
            @RequestParam(name="tag") Integer tag,
            @RequestParam(name="productId") List<Long> productId
    ) throws Exception {
        CosmeticCompareAjaxJsonRequestDTO requestDTO = CosmeticCompareAjaxJsonRequestDTO.builder().tag(tag).productId(productId).build();
        return new ResponseEntity<>(
                expoProductService.getExpoProductCompareOffcanvasAjaxJson(id, requestDTO, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)),
                HttpStatus.OK
        );
    }

}
