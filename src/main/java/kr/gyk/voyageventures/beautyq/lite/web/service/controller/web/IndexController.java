package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class IndexController {

    @GetMapping()
    public String getIndex (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        return "index";
    }

}
