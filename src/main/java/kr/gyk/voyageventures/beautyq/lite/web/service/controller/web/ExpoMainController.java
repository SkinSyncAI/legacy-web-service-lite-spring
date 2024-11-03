package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
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

    @GetMapping()
    public String getExpoMain (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        model.addAttribute("tags", expoMainService.getExpoMainTagListDTO());
        model.addAttribute("diagnosisResult", expoDiagnosisService.getSkinTypeResult(cookieComponent.getDiagnosisSkinType(httpServletRequest)));
        return "main";
    }

}
