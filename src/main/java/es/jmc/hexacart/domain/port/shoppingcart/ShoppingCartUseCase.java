package es.jmc.hexacart.domain.port.shoppingcart;

public interface ShoppingCartUseCase {

  ShoppingCartLite create();

  ShoppingCartFull get(long id);

  void delete(long id);

  void complete(long id);

  ShoppingCartFull addProduct(long id, long productId, int quantity);

  void removeProduct(long id, long productId);
}
