package es.jmc.hexacart.domain.port.shoppingcart;

import es.jmc.hexacart.domain.ShoppingCart;

public record ShoppingCartLite
    (
        long id,
        ShoppingCart.CartStatus status
    ) { }
