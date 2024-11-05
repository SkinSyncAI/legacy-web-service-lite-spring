package kr.gyk.voyageventures.beautyq.lite.web.service.service.api;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.api.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.entity.*;
import kr.gyk.voyageventures.beautyq.lite.web.service.exception.EntityDataNotFoundException;
import kr.gyk.voyageventures.beautyq.lite.web.service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class APICosmeticService {
    private final CosmeticRepository cosmeticRepository;
    private final CosmeticBrandRepository cosmeticBrandRepository;
    private final CosmeticCategoryRepository cosmeticCategoryRepository;
    private final CosmeticIngredientRepository cosmeticIngredientRepository;
    private final ImageRepository imageRepository;

    private final APICosmeticBrandService apiCosmeticBrandService;
    private final APICosmeticCategoryService apiCosmeticCategoryService;
    private final APICosmeticIngredientService apiCosmeticCategoryIngredientService;

    public APIGetCosmeticDTO getAPICosmeticInfoById (Long id) {
        Cosmetic cosmetic = cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new);

        APICosmeticBrandInfoDTO brandDTO = apiCosmeticBrandService.getAPICosmeticBrandInfoById(cosmetic.getBrand().getId());

        List<APICosmeticCategoryInfoDTO> listCategoryDTO = new ArrayList<>();
        for (CosmeticCategory category : cosmetic.getCategory())
            listCategoryDTO.add(apiCosmeticCategoryService.getAPICosmeticCategoryInfoById(category.getId()));

        List<APICosmeticIngredientInfoDTO> listIngredientDTO = new ArrayList<>();
        for (CosmeticIngredient ingredient : cosmetic.getIngredient())
            listIngredientDTO.add(apiCosmeticCategoryIngredientService.getAPICosmeticIngredientInfoById(ingredient.getId()));

        List<APIImageDTO> listImageDTO = new ArrayList<>();
        for (Image image : cosmetic.getImageThumbnails()) listImageDTO.add(new APIImageDTO(image));

        return APIGetCosmeticDTO.builder()
                .id(cosmetic.getId())
                .brand(brandDTO)
                .nameKo(cosmetic.getNameKo())
                .nameEn(cosmetic.getNameEn())
                .category(listCategoryDTO)
                .ingredient(listIngredientDTO)
                .price(cosmetic.getPrice())
                .priceDiscount(cosmetic.getPriceDiscount())
                .shippingFee(cosmetic.getShippingFee())
                .volume(cosmetic.getVolume())
                .rating(cosmetic.getRating())
                .countReview(cosmetic.getCountReview())
                .countPurchase(cosmetic.getCountPurchase())
                .imageProduct(new APIImageDTO(cosmetic.getImageProduct()))
                .imageThumbnails(listImageDTO)
                .typeScoreD(cosmetic.getTypeScoreD())
                .typeScoreO(cosmetic.getTypeScoreO())
                .typeScoreS(cosmetic.getTypeScoreS())
                .typeScoreR(cosmetic.getTypeScoreR())
                .typeScoreP(cosmetic.getTypeScoreP())
                .typeScoreN(cosmetic.getTypeScoreN())
                .typeScoreW(cosmetic.getTypeScoreW())
                .typeScoreT(cosmetic.getTypeScoreT())
                .keyword(cosmetic.getKeyword())
                .scoreHydration(cosmetic.getScoreHydration())
                .scoreSoothing(cosmetic.getScoreSoothing())
                .scoreBrightening(cosmetic.getScoreBrightening())
                .scoreBarrier(cosmetic.getScoreBarrier())
                .scoreMoisture(cosmetic.getScoreMoisture())
                .ingredientKeyword(cosmetic.getIngredientKeyword())
                .ingredientMatchingGood(cosmetic.getIngredientMatchingGood())
                .ingredientMatchingBad(cosmetic.getIngredientMatchingBad())
                .ingredientProhibit(cosmetic.getIngredientProhibit())
                .build();
    }

    public Boolean postAPICosmeticInfo (APIPostCosmeticDTO apiPostCosmeticDTO) {
        List<CosmeticCategory> listCategory = new ArrayList<>();
        List<CosmeticIngredient> listIngredient = new ArrayList<>();
        for (Integer id : apiPostCosmeticDTO.getCategory()) listCategory.add(cosmeticCategoryRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
        for (Integer id : apiPostCosmeticDTO.getIngredient()) listIngredient.add(cosmeticIngredientRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
        CosmeticBrand cosmeticBrand = cosmeticBrandRepository.findById(apiPostCosmeticDTO.getBrand()).orElseThrow(EntityDataNotFoundException::new);
        List<Image> listImage = new ArrayList<>();
        for (String id : apiPostCosmeticDTO.getImageThumbnails()) listImage.add(imageRepository.save(Image.builder().url(id).build()));
        Cosmetic newCosmetic = Cosmetic.builder()
                .brand(cosmeticBrand)
                .nameKo(apiPostCosmeticDTO.getNameKo())
                .nameEn(apiPostCosmeticDTO.getNameEn())
                .category(listCategory)
                .ingredient(listIngredient)
                .price(apiPostCosmeticDTO.getPrice())
                .priceDiscount(apiPostCosmeticDTO.getPriceDiscount())
                .shippingFee(apiPostCosmeticDTO.getShippingFee())
                .volume(apiPostCosmeticDTO.getVolume())
                .rating(apiPostCosmeticDTO.getRating())
                .countReview(apiPostCosmeticDTO.getCountReview())
                .countPurchase(apiPostCosmeticDTO.getCountPurchase())
                .imageProduct(imageRepository.save(Image.builder().url(apiPostCosmeticDTO.getImageProduct()).build()))
                .imageThumbnails(listImage)
                .typeScoreD(apiPostCosmeticDTO.getTypeScoreD())
                .typeScoreO(apiPostCosmeticDTO.getTypeScoreO())
                .typeScoreS(apiPostCosmeticDTO.getTypeScoreS())
                .typeScoreR(apiPostCosmeticDTO.getTypeScoreR())
                .typeScoreP(apiPostCosmeticDTO.getTypeScoreP())
                .typeScoreN(apiPostCosmeticDTO.getTypeScoreN())
                .typeScoreW(apiPostCosmeticDTO.getTypeScoreW())
                .typeScoreT(apiPostCosmeticDTO.getTypeScoreT())
                .keyword(apiPostCosmeticDTO.getKeyword())
                .scoreHydration(apiPostCosmeticDTO.getScoreHydration())
                .scoreSoothing(apiPostCosmeticDTO.getScoreSoothing())
                .scoreBrightening(apiPostCosmeticDTO.getScoreBrightening())
                .scoreBarrier(apiPostCosmeticDTO.getScoreBarrier())
                .scoreMoisture(apiPostCosmeticDTO.getScoreMoisture())
                .ingredientKeyword(apiPostCosmeticDTO.getIngredientKeyword())
                .ingredientMatchingGood(apiPostCosmeticDTO.getIngredientMatchingGood())
                .ingredientMatchingBad(apiPostCosmeticDTO.getIngredientMatchingBad())
                .ingredientProhibit(apiPostCosmeticDTO.getIngredientProhibit())
                .build();
        newCosmetic = cosmeticRepository.save(newCosmetic);

        cosmeticBrand.getCosmetic().add(newCosmetic);
        cosmeticBrandRepository.save(cosmeticBrand);

        return true;
    }

    public Boolean deleteAPICosmeticInfoById (Long id) {
        try {
            cosmeticRepository.delete(cosmeticRepository.findById(id).orElseThrow(EntityDataNotFoundException::new));
        } catch (Exception e) { throw new EntityDataNotFoundException(); }

        return true;
    }

}
