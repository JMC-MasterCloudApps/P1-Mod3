package es.jmc.hexacart.controller;

public record NewProductRequest
    (
        String brand,
        String name,
        int stock
    ) { }
