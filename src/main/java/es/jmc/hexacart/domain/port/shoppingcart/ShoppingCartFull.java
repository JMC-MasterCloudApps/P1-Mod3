package es.jmc.hexacart.domain.port.shoppingcart;

import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.product.ProductFull;
import java.util.Set;

public record ShoppingCartFull
    (
        long id,
        ShoppingCart.CartStatus status,
        Set<ProductFull> products
    ) { }
