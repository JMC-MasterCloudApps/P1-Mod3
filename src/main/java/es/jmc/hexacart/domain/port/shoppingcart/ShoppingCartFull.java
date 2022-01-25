package es.jmc.hexacart.domain.port.shoppingcart;

import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.product.ProductFull;
import java.util.Map;

public record ShoppingCartFull
    (
        long id,
        ShoppingCart.CartStatus status,
        Map<ProductFull, Integer> products
    ) { }
