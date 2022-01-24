package es.jmc.hexacart.infrastructure.repository;

import es.jmc.hexacart.infrastructure.model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductData, Long> {
}
