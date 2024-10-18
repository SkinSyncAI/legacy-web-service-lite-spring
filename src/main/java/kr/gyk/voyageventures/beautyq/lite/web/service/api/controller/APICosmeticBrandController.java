package kr.gyk.voyageventures.beautyq.lite.web.service.api.controller;

import kr.gyk.voyageventures.beautyq.lite.web.service.api.dto.APICosmeticBrandInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.api.service.APIAuthenticationService;
import kr.gyk.voyageventures.beautyq.lite.web.service.api.service.APICosmeticBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class APICosmeticBrandController {
    private final APIAuthenticationService apiAuthenticationService;
    private final APICosmeticBrandService apiCosmeticBrandService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APICosmeticBrandInfoDTO> getAPICosmeticBrandInfoById (@RequestParam("token") String token, @PathVariable("id") Integer id) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.getAPICosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<APICosmeticBrandInfoDTO> getAPICosmeticBrandInfoByCode (@RequestParam("token") String token, @PathVariable("code") String code) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.getAPICosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postAPICosmeticBrand (@RequestParam("token") String token, @RequestBody APICosmeticBrandInfoDTO apiCosmeticBrandInfoDTO) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.postAPICosmeticBrand(apiCosmeticBrandInfoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAPICosmeticBrandById (@RequestParam("token") String token, @PathVariable("id") Integer id) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.deleteAPICosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Boolean> deleteAPICosmeticBrandByCode (@RequestParam("token") String token, @PathVariable("code") String code) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.deleteAPICosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    // TODO :: @GetMapping("/{id}/detail")

}
