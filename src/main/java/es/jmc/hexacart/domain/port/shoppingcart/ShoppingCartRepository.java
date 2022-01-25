package es.jmc.hexacart.domain.port.shoppingcart;

public interface ShoppingCartRepository {

  ShoppingCartFull save(ShoppingCartNew shoppingCart);

  ShoppingCartFull save(ShoppingCartFull shoppingCart);

  ShoppingCartFull findById(long id);

  void deleteById(long id);
}
