package es.jmc.hexacart.domain.port.shoppingcart;

public interface ShoppingCartRepository {

  ShoppingCartFull save(ShoppingCartNew shoppingCart);

  ShoppingCartFull findById(long id);
}
