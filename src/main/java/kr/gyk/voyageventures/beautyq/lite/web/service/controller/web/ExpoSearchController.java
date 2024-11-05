package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/search")
public class ExpoSearchController {
    private final ExpoSearchService expoSearchService;
    private final CookieComponent cookieComponent;

    @GetMapping("/camera")
    public String getExpoCameraSearch (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        return "search_camera";
    }

    @GetMapping("/product/{id}")
    public String getExpoProductSearch (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("product", expoSearchService.getSearchProductDTO(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
        return "search_product";
    }

}
