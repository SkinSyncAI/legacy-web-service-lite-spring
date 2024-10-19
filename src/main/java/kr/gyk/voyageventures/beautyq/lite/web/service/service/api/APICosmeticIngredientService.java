package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import jakarta.validation.ConstraintViolationException;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticIngredientInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticIngredient;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityCodeDuplicatedException;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APICosmeticIngredientService {
    private final CosmeticIngredientRepository cosmeticIngredientRepository;

    public APICosmeticIngredientInfoDTO getAPICosmeticIngredientInfoById (Integer id) {
        CosmeticIngredient cosmeticIngredient = cosmeticIngredientRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticIngredientInfoDTO(cosmeticIngredient);
    }

    public APICosmeticIngredientInfoDTO getAPICosmeticIngredientInfoByCode (String code) {
        CosmeticIngredient cosmeticIngredient = cosmeticIngredientRepository.findByCode(code).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticIngredientInfoDTO(cosmeticIngredient);
    }

    public Boolean postAPICosmeticIngredient (APICosmeticIngredientInfoDTO apiCosmeticIngredientInfoDTO) {
        try {
            CosmeticIngredient cosmeticIngredient = CosmeticIngredient.builder()
                    .code(apiCosmeticIngredientInfoDTO.getCode())
                    .nameKo(apiCosmeticIngredientInfoDTO.getNameKo())
                    .nameEn(apiCosmeticIngredientInfoDTO.getNameEn())
                    .build();
            cosmeticIngredientRepository.save(cosmeticIngredient);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) { throw new EntityCodeDuplicatedException(); }

        return true;
    }

    public Boolean deleteAPICosmeticIngredientInfoById (Integer id) {
        try {
            cosmeticIngredientRepository.deleteById(id);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

    public Boolean deleteAPICosmeticIngredientInfoByCode (String code) {
        try {
            cosmeticIngredientRepository.deleteByCode(code);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

}
