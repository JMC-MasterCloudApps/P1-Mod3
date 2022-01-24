package es.jmc.hexacart.infrastructure;

import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartNew;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartRepository;
import es.jmc.hexacart.infrastructure.model.ShoppingCartData;
import es.jmc.hexacart.infrastructure.repository.ShoppingCartJpaRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

  private final ShoppingCartJpaRepository jpa;

  @Override
  public ShoppingCartFull save(ShoppingCartNew shoppingCartNew) {

    var data = map(shoppingCartNew);
    data = jpa.save(data);
    return map(data);
  }

  @Override
  public ShoppingCartFull findById(long id) {
    var data = jpa.findById(id);

    return data.map(ShoppingCartRepositoryAdapter::map).orElseThrow();
  }

  @Override
  public void deleteById(long id) {
    jpa.deleteById(id);
  }

  static ShoppingCartData map(ShoppingCartNew dto) {

    var result = new ShoppingCartData();
    result.setStatus(dto.status());

    var products = dto.products().stream()
        .map(ProductRepositoryAdapter::map)
        .collect(toSet());

    result.setProducts(products);

    return result;
  }

  static ShoppingCartFull map(ShoppingCartData data) {

    var productsFull = data.getProducts().stream()
        .map(ProductRepositoryAdapter::map)
        .collect(toSet());

    return new ShoppingCartFull(
        data.getId(),
        data.getStatus(),
        productsFull);
  }
}
