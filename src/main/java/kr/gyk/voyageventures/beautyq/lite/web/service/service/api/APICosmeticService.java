package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.Cosmetic;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticBrand;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticCategory;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticIngredient;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticBrandRepository;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticCategoryRepository;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticIngredientRepository;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.CosmeticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class APICosmeticService {
    private final CosmeticRepository cosmeticRepository;
    private final CosmeticBrandRepository cosmeticBrandRepository;
    private final CosmeticCategoryRepository cosmeticCategoryRepository;
    private final CosmeticIngredientRepository cosmeticIngredientRepository;

    private final APICosmeticBrandService apiCosmeticBrandService;
    private final APICosmeticCategoryService apiCosmeticCategoryService;
    private final APICosmeticIngredientService apiCosmeticCategoryIngredientService;

    public APIGetCosmeticDTO getAPICosmeticInfoById (Long id) throws Exception {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        APICosmeticBrandInfoDTO brandDTO = apiCosmeticBrandService.getAPICosmeticBrandInfoById(cosmetic.getBrand().getId());

        List<APICosmeticCategoryInfoDTO> listCategoryDTO = new ArrayList<>();
        for (CosmeticCategory category : cosmetic.getCategory())
            listCategoryDTO.add(apiCosmeticCategoryService.getAPICosmeticCategoryInfoById(category.getId()));

        List<APICosmeticIngredientInfoDTO> listIngredientDTO = new ArrayList<>();
        for (CosmeticIngredient ingredient : cosmetic.getIngredient())
            listIngredientDTO.add(apiCosmeticCategoryIngredientService.getAPICosmeticIngredientInfoById(ingredient.getId()));

        return APIGetCosmeticDTO.builder()
                .id(cosmetic.getId())
                .brand(brandDTO)
                .nameKo(cosmetic.getNameKo())
                .nameEn(cosmetic.getNameEn())
                .category(listCategoryDTO)
                .ingredient(listIngredientDTO)
                .image(cosmetic.getImage())
                .tag(cosmetic.getTag())
                .rating(cosmetic.getRating())
                .countReview(cosmetic.getCountReview())
                .cost(cosmetic.getCost())
                .discount(cosmetic.getDiscount())
                .howToUse(cosmetic.getHowToUse())
                .recommendSkin(cosmetic.getRecommendSkin())
                .build();
    }

    public Boolean postAPICosmeticInfo (APIPostCosmeticDTO apiPostCosmeticDTO) throws Exception {
        List<CosmeticCategory> listCategory = new ArrayList<>();
        List<CosmeticIngredient> listIngredient = new ArrayList<>();
        for (Integer id : apiPostCosmeticDTO.getCategory()) listCategory.add(cosmeticCategoryRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
        for (Integer id : apiPostCosmeticDTO.getIngredient()) listIngredient.add(cosmeticIngredientRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
        CosmeticBrand cosmeticBrand = cosmeticBrandRepository.findById(apiPostCosmeticDTO.getBrand()).orElseThrow(EntityDataNotFoundException::new);
        Cosmetic newCosmetic = Cosmetic.builder()
                .brand(cosmeticBrand)
                .nameKo(apiPostCosmeticDTO.getNameKo())
                .nameEn(apiPostCosmeticDTO.getNameEn())
                .category(listCategory)
                .ingredient(listIngredient)
                .image(apiPostCosmeticDTO.getImage())
                .tag(apiPostCosmeticDTO.getTag())
                .rating(apiPostCosmeticDTO.getRating())
                .countReview(apiPostCosmeticDTO.getCountReview())
                .cost(apiPostCosmeticDTO.getCost())
                .discount(apiPostCosmeticDTO.getDiscount())
                .howToUse(apiPostCosmeticDTO.getHowToUse())
                .recommendSkin(apiPostCosmeticDTO.getRecommendSkin())
                .build();
        cosmeticRepository.save(newCosmetic);

        return true;
    }

    public Boolean deleteAPICosmeticInfoById (Long id) throws Exception {
        try {
            cosmeticRepository.deleteById(id);
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

}
