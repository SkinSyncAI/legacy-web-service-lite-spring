package kr.gyk.voyageventures.beautyq.lite.web.service.controller.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticCategoryInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APIAuthenticationService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APICosmeticCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class APICosmeticCategoryController {
    private final APIAuthenticationService apiAuthenticationService;
    private final APICosmeticCategoryService apiCosmeticCategoryService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APICosmeticCategoryInfoDTO> getAPICosmeticCategoryInfoById (@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(apiCosmeticCategoryService.getAPICosmeticCategoryInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<APICosmeticCategoryInfoDTO> getAPICosmeticCategoryInfoByCode (@PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(apiCosmeticCategoryService.getAPICosmeticCategoryInfoByCode(code), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postAPICosmeticCategory (@RequestParam("token") String token, @RequestBody APICosmeticCategoryInfoDTO apiCosmeticCategoryInfoDTO) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticCategoryService.postAPICosmeticCategory(apiCosmeticCategoryInfoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAPICosmeticCategoryById (@RequestParam("token") String token, @PathVariable("id") Integer id) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticCategoryService.deleteAPICosmeticCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Boolean> deleteAPICosmeticCategoryByCode (@RequestParam("token") String token, @PathVariable("code") String code) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticCategoryService.deleteAPICosmeticCategoryByCode(code), HttpStatus.OK);
    }

}
