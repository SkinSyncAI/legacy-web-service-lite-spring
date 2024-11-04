package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.CosmeticService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoDiagnosisService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/main")
public class ExpoMainController {
    private final CookieComponent cookieComponent;

    private final ExpoMainService expoMainService;
    private final ExpoDiagnosisService expoDiagnosisService;
    private final CosmeticService cosmeticService;

    @GetMapping()
    public String getExpoMain (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        model.addAttribute("tags", cosmeticService.getExpoMainTagListDTO());
        model.addAttribute("diagnosisResult", expoDiagnosisService.getSkinTypeResult(cookieComponent.getDiagnosisSkinType(httpServletRequest)));
        model.addAttribute("cosmeticList", expoMainService.getExpoMainCosmeticListDTO(cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
        return "main";
    }

}
