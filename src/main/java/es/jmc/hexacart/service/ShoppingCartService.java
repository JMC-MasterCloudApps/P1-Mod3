package es.jmc.hexacart.service;

import es.jmc.hexacart.controller.ShoppingCartLiteResponse;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartLite;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartService {

  private final ShoppingCartUseCase useCase;

  public ShoppingCartLiteResponse createShoppingCart() {
    return map(useCase.create());
  }

  private static ShoppingCartLiteResponse map(ShoppingCartLite dto) {

    return new ShoppingCartLiteResponse(dto.id(), dto.status().name());
  }

}
