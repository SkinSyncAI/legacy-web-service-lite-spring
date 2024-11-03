package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/product")
public class ExpoProductController {

    @GetMapping("/{id}")
    public String getExpoProduct (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        return "product";
    }

    @GetMapping("/{id}/compare")
    public String getExpoProductCompare (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        return "product_compare";
    }

}
