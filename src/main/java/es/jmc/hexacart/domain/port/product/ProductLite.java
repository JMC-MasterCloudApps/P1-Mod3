package es.jmc.hexacart.domain.port.product;

public record ProductLite
    (
        String brand,
        String name,
        int stock
    ) { }
