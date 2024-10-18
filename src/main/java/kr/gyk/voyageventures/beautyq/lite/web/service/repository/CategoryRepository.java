package kr.gyk.voyageventures.beautyq.lite.web.service.repository;

import kr.gyk.voyageventures.beautyq.lite.web.service.entity.CosmeticCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CosmeticCategory, Integer> {
}
