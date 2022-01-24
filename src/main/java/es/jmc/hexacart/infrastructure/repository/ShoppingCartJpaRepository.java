package es.jmc.hexacart.infrastructure.repository;

import es.jmc.hexacart.infrastructure.model.ShoppingCartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartData, Long> {


}
