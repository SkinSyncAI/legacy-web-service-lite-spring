package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/event")
public class ExpoEventController {
    private final CookieComponent cookieComponent;
    private final ExpoEventService expoEventService;

    @GetMapping("/{id}/pick")
    public String getExpoEventPick (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("id", id);
        return "event_pick";
    }

    @GetMapping("/{id}/result")
    public String getExpoEventResult (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("product", expoEventService.getExpoEventResult(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
        cookieComponent.setEvent(false, httpServletResponse);
        return "event_result";
    }

}
