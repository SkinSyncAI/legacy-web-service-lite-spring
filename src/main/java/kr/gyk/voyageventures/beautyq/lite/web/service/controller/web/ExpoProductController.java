package kr.gyk.voyageventures.beautyq.lite.web.service.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.CookieComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.component.ScoringComponent;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.MainTagForm;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.CosmeticService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.web.ExpoProductService;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.classgen.asm.MopWriter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expo/product")
public class ExpoProductController {
    private final CookieComponent cookieComponent;
    private final ExpoProductService expoProductService;
    private final CosmeticService cosmeticService;

    @GetMapping("/{id}")
    public String getExpoProduct (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id
    ) throws Exception {
        model.addAttribute("product", expoProductService.getCosmeticDetailDTO(id));
        model.addAttribute("productScore", expoProductService.getCosmeticScore(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
        model.addAttribute("productSim", expoProductService.getCosmeticListSimilarDTO(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));
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
        model.addAttribute("offcanvas_productSim", expoProductService.getCosmeticScoreListDTOSimilar(id, (short) 0, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest)));

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
        CosmeticScoreListDTO cosmeticListDTO = expoProductService.getCosmeticScoreListDTOSimilar(id, tag, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest));
        CosmeticScoreListElementDTO cosmeticCurrent = expoProductService.getCosmeticScoreListDTOCurrent(id, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest));

        CosmeticScoreListElementDTO firstDTO = null;
        List<CosmeticScoreListElementDTO> newListElementDTO = new ArrayList<>();

        if (cosmeticListDTO.getCount() > 1 && cosmeticListDTO.getCosmeticList().getFirst().getMatching() > cosmeticCurrent.getMatching()) {
            firstDTO = cosmeticListDTO.getCosmeticList().getFirst();
            newListElementDTO = cosmeticListDTO.getCosmeticList().stream().skip(1).toList();
        } else if (cosmeticListDTO.getCount() > 1) {
            newListElementDTO = cosmeticListDTO.getCosmeticList();
        }

        model.addAttribute("isTopNull", firstDTO == null ? 1 : 0);
        model.addAttribute("productTop", firstDTO);
        model.addAttribute("productSim", CosmeticScoreListDTO.builder().count((long) newListElementDTO.size()).cosmeticList(newListElementDTO).build());

        return "product_compare_ajax";
    }

    /** Offcanvas */

    @GetMapping("/{id}/compare/offcanvas/ajax/html")
    public String getExpoProductCompareOffcanvasAjaxHtml (
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable(name="id") Long id,
            @RequestParam(name="tag") Integer tag,
            @RequestParam(name="productId") List<Long> productId
    ) throws Exception {
        CosmeticCompareAjaxJsonRequestDTO requestDTO = CosmeticCompareAjaxJsonRequestDTO.builder().tag(tag).productId(productId).build();

        CosmeticMatchingListDTO graphDTO = expoProductService.getExpoProductCompareOffcanvasAjaxHtmlGraph(id, requestDTO, cookieComponent.getDiagnosisSkinType(httpServletRequest), cookieComponent.getMainTag(httpServletRequest), cookieComponent.getScoringRandom(httpServletRequest));

        List<CosmeticCompareAjaxGraphDTO> destDTO = new ArrayList<>();
        for (var src : graphDTO.getCosmeticList()) {
            CosmeticCompareAjaxGraphDTO newDTO = new CosmeticCompareAjaxGraphDTO();
            newDTO.setId(src.getId());
            newDTO.setName(src.getName());
            if (requestDTO.getTag() == 0) newDTO.setScore(src.getScoreAll());
            else if (requestDTO.getTag() == 1) newDTO.setScore(src.getScoreHydration());
            else if (requestDTO.getTag() == 2) newDTO.setScore(src.getScoreMoisture());
            else if (requestDTO.getTag() == 3) newDTO.setScore(src.getScoreBarrier());
            else if (requestDTO.getTag() == 4) newDTO.setScore(src.getScoreSoothing());
            else if (requestDTO.getTag() == 5) newDTO.setScore(src.getScoreBrightening());
            destDTO.add(newDTO);
        }

        model.addAttribute("productGraphCurrent", destDTO.getFirst());
        model.addAttribute("productGraph", destDTO.size() > 1 ? destDTO.stream().skip(1).toList() : new ArrayList<>());

        model.addAttribute("productCompare", expoProductService.getExpoProductCompareOffcanvasAjaxHtmlCompare(graphDTO));

        return "product_compare_offcanvas_ajax";
    }
}
