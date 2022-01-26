package es.jmc.hexacart.domain;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.COMPLETE;
import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingCart {

  private long id;
  private CartStatus status;
  private Map<Product, Integer> quantityByProduct;

  public enum CartStatus {
    OPEN,
    COMPLETE;

    public static CartStatus defaultValue() {
      return OPEN;
    }
  }

  public ShoppingCart() {
    status = CartStatus.OPEN;
    quantityByProduct = new HashMap<>();
  }

  public void complete() {
    status = COMPLETE;
  }

  public boolean isComplete() {
    return COMPLETE.equals(status);
  }

  public int getQuantity(long productId) {

    return quantityByProduct.entrySet().stream()
        .filter(product -> product.getKey().getId() == productId)
        .findAny()
        .map(Map.Entry::getValue)
        .orElse(0);
  }

  public void addProduct(Product product, int quantity) {

    var productMatch = quantityByProduct.keySet().stream()
        .filter(item -> item.getId() == product.getId())
        .findAny();

    quantityByProduct.put(productMatch.orElse(product), quantity);
  }

  public Integer removeProduct(Product product) {

    var match = quantityByProduct.entrySet().stream()
        .filter(entry -> entry.getKey().getId() == product.getId())
        .findAny();

    match.map(Map.Entry::getKey).ifPresent(quantityByProduct::remove);

    return match.map(Map.Entry::getValue).orElse(0);
  }
}
