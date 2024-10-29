package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoDiagnosisResultDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/diagnosis")
public class ExpoDiagnosisController {
    private final CookieComponent cookieComponent;

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
        List<Integer> needs = new ArrayList<>();
        needs.add(1);
        needs.add(2);
        needs.add(3);
        ExpoDiagnosisResultDTO expoDiagnosisResultDTO = ExpoDiagnosisResultDTO.builder().skintype("지성피부").needs(needs).build();

        model.addAttribute("resultDTO", expoDiagnosisResultDTO);
        return "diagnosis_result";
    }

}
