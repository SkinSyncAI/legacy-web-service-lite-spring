package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoDiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/diagnosis")
public class ExpoDiagnosisController {
    private final CookieComponent cookieComponent;

    private final ExpoDiagnosisService expoDiagnosisService;

    @GetMapping("/intro")
    public String getExpoDiagnosisStart (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        return "diagnosis_intro";
    }

    @GetMapping("/test")
    public String getExpoDiagnosisTest (
        Model model,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        DiagnosisTestForm diagnosisTestForm
    ) throws Exception {
        return "diagnosis_test";
    }

    @PostMapping("/test")
    public String postExpoDiagnosisTest (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @Valid DiagnosisTestForm diagnosisTestForm
    ) throws Exception {
        cookieComponent.setDiagnosisSkinType(diagnosisTestForm, httpServletResponse);
        cookieComponent.initSession(httpServletResponse);
        return "redirect:/expo/diagnosis/result";
    }

    @GetMapping("/result")
    public String getExpoDiagnosisResult (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        model.addAttribute("resultDTO", expoDiagnosisService.getSkinTypeResult(cookieComponent.getDiagnosisSkinType(httpServletRequest)));
        return "diagnosis_result";
    }

    @GetMapping("/camera")
    public String getExpoCameraDiagnosis (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        return "diagnosis_camera";
    }

}
