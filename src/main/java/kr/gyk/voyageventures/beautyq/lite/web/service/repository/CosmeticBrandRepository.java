package kr.gyk.voyageventures.beautyq.lite.web.service.repository;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CosmeticBrandRepository extends JpaRepository<CosmeticBrand, Integer> {
    Optional<CosmeticBrand> findByCode (String code);
    void deleteByCode (String code);
}
