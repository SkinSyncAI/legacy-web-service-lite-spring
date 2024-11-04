package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import jakarta.validation.ConstraintViolationException;
import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.APICosmeticCategoryInfoDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticCategory;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityCodeDuplicatedException;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APICosmeticCategoryService {
    private final CosmeticCategoryRepository cosmeticCategoryRepository;

    public APICosmeticCategoryInfoDTO getAPICosmeticCategoryInfoById (Integer id) {
        CosmeticCategory cosmeticCategory = cosmeticCategoryRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticCategoryInfoDTO(cosmeticCategory);
    }

    public APICosmeticCategoryInfoDTO getAPICosmeticCategoryInfoByCode (String code) {
        CosmeticCategory cosmeticCategory = cosmeticCategoryRepository.findByCode(code).orElseThrow(EntityDataNotFoundException::new);

        return new APICosmeticCategoryInfoDTO(cosmeticCategory);
    }

    public Boolean postAPICosmeticCategory (String code, String nameKo, String nameEn) {
        try {
            CosmeticCategory cosmeticCategory = CosmeticCategory.builder()
                    .code(code)
                    .nameKo(nameKo)
                    .nameEn(nameEn)
                    .build();
            cosmeticCategoryRepository.save(cosmeticCategory);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) { throw new EntityCodeDuplicatedException(); }

        return true;
    }

    public Boolean deleteAPICosmeticCategoryById (Integer id) {
        try {
            cosmeticCategoryRepository.deleteById(id);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

    public Boolean deleteAPICosmeticCategoryByCode (String code) {
        try {
            cosmeticCategoryRepository.deleteByCode(code);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

}
