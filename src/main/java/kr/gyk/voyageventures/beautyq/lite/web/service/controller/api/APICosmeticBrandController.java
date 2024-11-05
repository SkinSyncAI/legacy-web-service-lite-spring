package kr.gyk.voyageventures.beautyq.lite.web.service.controller.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticBrandInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APIAuthenticationService;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.api.APICosmeticBrandService;
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
    public ResponseEntity<APICosmeticBrandInfoDTO> getAPICosmeticBrandInfoById (
            @PathVariable("id") Integer id
    ) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.getAPICosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<APICosmeticBrandInfoDTO> getAPICosmeticBrandInfoByCode (
            @PathVariable("code") String code
    ) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.getAPICosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postAPICosmeticBrand (
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "nameKo") String nameKo,
            @RequestParam(value = "nameEn", required = false) String nameEn
    ) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.postAPICosmeticBrand(code, nameKo, nameEn), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAPICosmeticBrandById (
            @RequestParam(value = "token", required = false) String token,
            @PathVariable("id") Integer id
    ) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.deleteAPICosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Boolean> deleteAPICosmeticBrandByCode (
            @RequestParam(value = "token", required = false) String token,
            @PathVariable("code") String code
    ) throws Exception {
        apiAuthenticationService.verifyAuthToken(token);
        return new ResponseEntity<>(apiCosmeticBrandService.deleteAPICosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    // TODO :: @GetMapping("/{id}/detail")

}
