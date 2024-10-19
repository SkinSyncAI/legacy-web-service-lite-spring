package kr.gyk.voyageventures.beautyq.lite.web.service.controller.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticIngredientInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APIAuthenticationService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APICosmeticIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredient")
public class APICosmeticIngredientController {
    private final APIAuthenticationService apiAuthenticationService;
    private final APICosmeticIngredientService apiCosmeticIngredientService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APICosmeticIngredientInfoDTO> getAPICosmeticIngredientInfoById (@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(apiCosmeticIngredientService.getAPICosmeticIngredientInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<APICosmeticIngredientInfoDTO> getAPICosmeticIngredientInfoByCode (@PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(apiCosmeticIngredientService.getAPICosmeticIngredientInfoByCode(code), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postAPICosmeticIngredient (@RequestParam("token") String token, @RequestBody APICosmeticIngredientInfoDTO apiCosmeticIngredientInfoDTO) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticIngredientService.postAPICosmeticIngredient(apiCosmeticIngredientInfoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAPICosmeticIngredientById (@RequestParam("token") String token, @PathVariable("id") Integer id) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticIngredientService.deleteAPICosmeticIngredientInfoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Boolean> deleteAPICosmeticIngredientByCode (@RequestParam("token") String token, @PathVariable("code") String code) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticIngredientService.deleteAPICosmeticIngredientInfoByCode(code), HttpStatus.OK);
    }

}
