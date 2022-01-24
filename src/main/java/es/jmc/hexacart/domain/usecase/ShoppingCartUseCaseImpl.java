package es.jmc.hexacart.domain.usecase;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.defaultValue;
import static java.util.Collections.emptySet;

import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartLite;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartNew;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartRepository;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

  private final ShoppingCartRepository repository;

  @Override
  public ShoppingCartLite create() {

    var newShoppingCart = new ShoppingCartNew(defaultValue(), emptySet());
    var result = repository.save(newShoppingCart);

    return map(result);
  }

  private ShoppingCartLite map(ShoppingCartFull dto) {

    return new ShoppingCartLite(
        dto.id(),
        dto.status());
  }

}
