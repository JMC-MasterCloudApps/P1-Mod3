package es.jmc.hexacart;

import es.jmc.hexacart.domain.port.product.ProductUseCase;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import es.jmc.hexacart.domain.usecase.ProductUseCaseImpl;
import es.jmc.hexacart.domain.usecase.ShoppingCartUseCaseImpl;
import es.jmc.hexacart.infrastructure.ProductRepositoryAdapter;
import es.jmc.hexacart.infrastructure.ShoppingCartRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration  {

  @Bean
  public ProductUseCase productUseCase(ProductRepositoryAdapter adapter) {
    return new ProductUseCaseImpl(adapter);
  }

  @Bean
  public ShoppingCartUseCase shoppingCartUseCase(ShoppingCartRepositoryAdapter adapter) {
    return new ShoppingCartUseCaseImpl(adapter);
  }

}
