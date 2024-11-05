package kr.gyk.voyageventures.beautyq.lite.web.service.repository;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CosmeticCategoryRepository extends JpaRepository<CosmeticCategory, Integer> {
    Optional<CosmeticCategory> findByCode (String code);
    void deleteByCode (String code);
}
