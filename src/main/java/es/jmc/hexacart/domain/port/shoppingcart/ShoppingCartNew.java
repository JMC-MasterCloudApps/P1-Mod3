package es.jmc.hexacart.domain.port.shoppingcart;

import es.jmc.hexacart.domain.ShoppingCart;
import es.jmc.hexacart.domain.port.product.ProductLite;
import java.util.Set;

public record ShoppingCartNew
    (
        ShoppingCart.CartStatus status,
        Set<ProductLite> products
    ) { }
