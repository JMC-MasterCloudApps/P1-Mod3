package es.jmc.hexacart;

import es.jmc.hexacart.domain.port.ProductUseCase;
import es.jmc.hexacart.domain.usecase.ProductUseCaseImpl;
import es.jmc.hexacart.infrastructure.ProductRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration  {

  @Bean
  public ProductUseCase productUseCase() {
    return new ProductUseCaseImpl(new ProductRepositoryAdapter());
  }

}
