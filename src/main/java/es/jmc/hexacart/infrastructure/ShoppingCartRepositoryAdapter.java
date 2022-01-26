package es.jmc.hexacart.infrastructure;

import static java.util.stream.Collectors.toMap;

import es.jmc.hexacart.domain.port.product.ProductRepository;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartNew;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartRepository;
import es.jmc.hexacart.infrastructure.model.ShoppingCartData;
import es.jmc.hexacart.infrastructure.repository.ShoppingCartJpaRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

  private final ShoppingCartJpaRepository jpa;
  private final ProductRepository productRepository;

  @Override
  public ShoppingCartFull save(ShoppingCartNew shoppingCartNew) {

    var data = map(shoppingCartNew);
    data = jpa.save(data);
    return map(data);
  }

  @Override
  public ShoppingCartFull save(ShoppingCartFull shoppingCart) {

    var data = map(shoppingCart);
    data = jpa.save(data);
    return map(data);
  }

  @Override
  public ShoppingCartFull findById(long id) {
    var data = jpa.findById(id);

    return data.map(this::map).orElseThrow();
  }

  @Override
  public void deleteById(long id) {
    jpa.deleteById(id);
  }

  static ShoppingCartData map(ShoppingCartNew dto) {

    var result = new ShoppingCartData();
    result.setStatus(dto.status());
    result.setProducts(dto.products());

    return result;
  }

  private ShoppingCartFull map(ShoppingCartData data) {

    var products = data.getProducts().entrySet().stream()
        .collect(toMap(
            entry -> productRepository.findById(entry.getKey()),
            Map.Entry::getValue
        ));

    return new ShoppingCartFull(
        data.getId(),
        data.getStatus(),
        products);
  }

  static ShoppingCartData map(ShoppingCartFull dto) {

    var products = dto.products().entrySet().stream()
        .collect(toMap(
            entry -> entry.getKey().id(),
            Map.Entry::getValue
        ));

    return new ShoppingCartData(
        dto.id(),
        dto.status(),
        products);
  }
}
