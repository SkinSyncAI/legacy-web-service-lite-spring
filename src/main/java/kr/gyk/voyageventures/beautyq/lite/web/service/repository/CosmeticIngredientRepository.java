package kr.gyk.voyageventures.beautyq.lite.web.service.repository;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CosmeticIngredientRepository extends JpaRepository<CosmeticIngredient, Integer> {
    Optional<CosmeticIngredient> findByCode (String code);
    void deleteByCode (String code);
}
