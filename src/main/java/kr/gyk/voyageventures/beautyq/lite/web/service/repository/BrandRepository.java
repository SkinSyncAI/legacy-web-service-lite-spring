package kr.gyk.voyageventures.beautyq.lite.web.service.repository;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<CosmeticBrand, Integer> {
}
