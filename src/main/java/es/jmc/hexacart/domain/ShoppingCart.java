package es.jmc.hexacart.domain;

import java.util.HashSet;
import java.util.Set;
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
}
