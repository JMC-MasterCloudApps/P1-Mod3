package es.jmc.hexacart.domain.usecase;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.defaultValue;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartLite;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartNew;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartRepository;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import java.util.stream.Collectors;
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

  @Override
  public ShoppingCartFull get(long id) {

    return repository.findById(id);
  }

  @Override
  public void delete(long id) {
    repository.deleteById(id);
  }

  @Override
  public void complete(long id) {

    ShoppingCartFull shoppingCartFull = get(id);
    var model = new ShoppingCart(shoppingCartFull);
    model.complete();
    repository.save(map(model));
  }

  private static ShoppingCartLite map(ShoppingCartFull dto) {

    return new ShoppingCartLite(
        dto.id(),
        dto.status());
  }

  private static ShoppingCartFull map(ShoppingCart model) {

    var products = model.getProducts().stream()
        .map(ProductUseCaseImpl::map)
        .collect(toSet());

    return new ShoppingCartFull(
        model.getId(),
        model.getStatus(),
        products);
  }
}
