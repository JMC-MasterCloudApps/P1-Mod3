package es.jmc.hexacart.domain.usecase;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.defaultValue;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.domain.Product;
import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.product.ProductFull;
import es.jmc.hexacart.domain.port.product.ProductRepository;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartLite;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartNew;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartRepository;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import es.jmc.hexacart.infrastructure.ProductRepositoryAdapter;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

  private final ShoppingCartRepository repository;
  private final ProductRepository productRepository;

  @Override
  public ShoppingCartLite create() {

    var newShoppingCart = new ShoppingCartNew(defaultValue(), emptyMap());
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

    var model = toModel(shoppingCartFull);
    model.complete();
    repository.save(map(model));
  }

  @Override
  public ShoppingCartFull addProduct(long id, long productId, int quantity) {

    var productFull = productRepository.findById(productId);
    var shoppingCart = toModel(repository.findById(id));

    int newStock = calculateNewStock(shoppingCart, productFull, quantity);

    productFull = new ProductFull(
        productId,
        productFull.brand(),
        productFull.name(),
        newStock);
     var product = new Product(productRepository.update(productFull));

    shoppingCart.addProduct(product, quantity);
    return repository.save(map(shoppingCart));
  }

  @Override
  public void removeProduct(long id, long productId) {
    var shoppingCart = toModel(repository.findById(id));
    var product = new Product(productRepository.findById(productId));

    int amountRemoved = shoppingCart.removeProduct(product);
    repository.save(map(shoppingCart));

    product.addStock(amountRemoved);
    productRepository.update(ProductUseCaseImpl.map(product));
  }

  private int calculateNewStock(ShoppingCart shoppingCart,
                                ProductFull productFull,
                                int quantity) {

    int quantityInCart = shoppingCart.getQuantity(productFull.id());
    int neededStock = quantity - quantityInCart;
    int newStock = productFull.stock() - neededStock;

    if (newStock < 0) {
      throw new RuntimeException("Not enough stock!");
    }

    return newStock;
  }

  private static ShoppingCartLite map(ShoppingCartFull dto) {

    return new ShoppingCartLite(
        dto.id(),
        dto.status());
  }

  private static ShoppingCartFull map(ShoppingCart model) {

    var products = model.getQuantityByProduct().entrySet().stream()
        .collect(Collectors.toMap(
            entry -> ProductUseCaseImpl.map(entry.getKey()),
            Map.Entry::getValue
        ));

    return new ShoppingCartFull(
        model.getId(),
        model.getStatus(),
        products);
  }

  private static ShoppingCart toModel(ShoppingCartFull dto) {

    var products = dto.products().entrySet().stream()
        .collect(Collectors.toMap(
            entry -> new Product(entry.getKey()),
            Map.Entry::getValue
        ));

    return new ShoppingCart(
        dto.id(),
        dto.status(),
        products);
  }
}
