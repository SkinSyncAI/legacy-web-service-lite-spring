package kr.gyk.voyageventures.beautyq.lite.web.service.controller;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.APICosmeticBrandInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.service.APICosmeticBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cosmetic/brand")
public class APICosmeticBrandController {
    private final APICosmeticBrandService apiCosmeticBrandService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APICosmeticBrandInfoDTO> getCosmeticBrandInfoById (@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.getCosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<APICosmeticBrandInfoDTO> getCosmeticBrandInfoByCode (@PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.getCosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> postCosmeticBrand (@RequestBody APICosmeticBrandInfoDTO apiCosmeticBrandInfoDTO) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.postCosmeticBrand(apiCosmeticBrandInfoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteCosmeticBrandById (@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.deleteCosmeticBrandInfoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Boolean> deleteCosmeticBrandByCode (@PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(apiCosmeticBrandService.deleteCosmeticBrandInfoByCode(code), HttpStatus.OK);
    }

    // TODO :: Delete

    // @GetMapping("/{id}/detail")

}
