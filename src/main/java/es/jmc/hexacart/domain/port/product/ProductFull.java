package es.jmc.hexacart.domain.port.product;

public record ProductFull
    (
        long id,
        String brand,
        String name,
        int stock
    ) { }
