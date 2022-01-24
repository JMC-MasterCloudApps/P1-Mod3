package es.jmc.hexacart.controller;

import es.jmc.hexacart.domain.ShoppingCart;
import java.util.Set;

public record ShoppingCartFullResponse
    (
        long id,
        ShoppingCart.CartStatus status,
        Set<ProductFullResponse> products
    ) { }
