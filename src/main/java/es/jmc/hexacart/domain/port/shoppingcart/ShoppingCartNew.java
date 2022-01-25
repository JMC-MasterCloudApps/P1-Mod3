package es.jmc.hexacart.domain.port.shoppingcart;

import es.jmc.hexacart.domain.ShoppingCart;
import java.util.Map;

public record ShoppingCartNew
    (
        ShoppingCart.CartStatus status,
        Map<Long, Integer> products
    ) { }
