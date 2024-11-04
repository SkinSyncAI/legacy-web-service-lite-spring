package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoProductService;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.classgen.asm.MopWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/product")
public class ExpoProductController {
    private final CookieComponent cookieComponent;
    private final ExpoProductService expoProductService;

    @GetMapping("/{id}")
    public String getExpoProduct (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("product", expoProductService.getCosmeticDetailDTO(id));
        model.addAttribute("productSim", expoProductService.getCosmeticListSimilarDTO(cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
        model.addAttribute("event", cookieComponent.getEvent(httpServletRequest));
        return "product";
    }

    @GetMapping("/{id}/compare")
    public String getExpoProductCompare (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("productCurrent", expoProductService.getCosmeticScoreListDTOCurrent(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));

        return "product_compare";
    }

    @GetMapping("/{id}/compare/ajax/{tag}")
    public String getExpoProductCompareAjax (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id,
            @PathVariable(name="tag") Short tag
    ) throws Exception {
        model.addAttribute("productSim", expoProductService.getCosmeticScoreListDTOSimilar(id, tag, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));

        return "product_compare_ajax:: #bq-recommend-content";
    }

}
