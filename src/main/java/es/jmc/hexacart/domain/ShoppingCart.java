package es.jmc.hexacart.domain;

import static es.jmc.hexacart.domain.ShoppingCart.CartStatus.COMPLETE;
import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ShoppingCart {

  private long id;
  private CartStatus status;
  Set<Product> products;

  public enum CartStatus {
    OPEN,
    COMPLETE;

    public static CartStatus defaultValue() {
      return OPEN;
    }
  }

  public ShoppingCart() {
    status = CartStatus.OPEN;
    products = new HashSet<>();
  }

  public ShoppingCart(ShoppingCartFull dto) {
    id = dto.id();
    status = dto.status();
    products = dto.products().stream()
        .map(Product::new)
        .collect(toSet());
  }

  public void complete() {
    status = COMPLETE;
  }
}
