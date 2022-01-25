package es.jmc.hexacart.domain.port.shoppingcart;

public interface ShoppingCartUseCase {

  ShoppingCartLite create();

  ShoppingCartFull get(long id);

  void delete(long id);

  void complete(long id);
}
