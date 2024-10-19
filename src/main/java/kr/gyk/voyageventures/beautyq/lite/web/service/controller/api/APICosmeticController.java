package kr.gyk.voyageventures.beautyq.lite.web.service.controller.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APIGetCosmeticDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APIPostCosmeticDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APIAuthenticationService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APICosmeticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cosmetic")
public class APICosmeticController {
    private final APIAuthenticationService authenticationService;
    private final APICosmeticService apiCosmeticService;

    @GetMapping("/{id}")
    public ResponseEntity<APIGetCosmeticDTO> getAPICosmeticInfoById (@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(apiCosmeticService.getAPICosmeticInfoById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postAPICosmeticInfo (@RequestParam("token") String token, @RequestBody APIPostCosmeticDTO apiPostCosmeticDTO) throws Exception {
        authenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticService.postAPICosmeticInfo(apiPostCosmeticDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAPICosmeticInfoById (@RequestParam("token") String token, @PathVariable("id") Long id) throws Exception {
        authenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticService.deleteAPICosmeticInfoById(id), HttpStatus.OK);
    }

}
