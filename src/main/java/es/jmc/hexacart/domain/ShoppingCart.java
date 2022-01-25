package es.jmc.hexacart.domain;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.COMPLETE;
import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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

  public int getQuantity(long productId) {

    // TODO
    return 0;
  }

  public void addProduct(Product product, int quantity) {

    quantityByProduct.put(product, quantity);
  }
}
