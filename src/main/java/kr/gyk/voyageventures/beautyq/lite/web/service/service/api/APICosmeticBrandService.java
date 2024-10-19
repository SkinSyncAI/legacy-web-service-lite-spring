package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import jakarta.validation.ConstraintViolationException;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticBrandInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticBrand;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityCodeDuplicatedException;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APICosmeticBrandService {
    private final CosmeticBrandRepository cosmeticBrandRepository;

    public APICosmeticBrandInfoDTO getAPICosmeticBrandInfoById (Integer id) throws Exception {
        CosmeticBrand cosmeticBrand = cosmeticBrandRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticBrandInfoDTO(cosmeticBrand);
    }

    public APICosmeticBrandInfoDTO getAPICosmeticBrandInfoByCode (String code) throws Exception {
        CosmeticBrand cosmeticBrand = cosmeticBrandRepository.findByCode(code).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticBrandInfoDTO(cosmeticBrand);
    }

    public Boolean postAPICosmeticBrand (APICosmeticBrandInfoDTO apiCosmeticBrandInfoDTO) throws Exception {
        try {
            CosmeticBrand cosmeticBrand = CosmeticBrand.builder()
                    .code(apiCosmeticBrandInfoDTO.getCode())
                    .nameKo(apiCosmeticBrandInfoDTO.getNameKo())
                    .nameEn(apiCosmeticBrandInfoDTO.getNameEn())
                    .build();
            cosmeticBrandRepository.save(cosmeticBrand);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) { throw new EntityCodeDuplicatedException(); }

        return true;
    }

    public Boolean deleteAPICosmeticBrandInfoById (Integer id) throws Exception {
        try {
            cosmeticBrandRepository.deleteById(id);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

    public Boolean deleteAPICosmeticBrandInfoByCode (String code) throws Exception {
        try {
            cosmeticBrandRepository.deleteByCode(code);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

}