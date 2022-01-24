package es.jmc.hexacart.controller;

import es.jmc.hexacart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingcarts/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartController {

  private final ShoppingCartService service;

  @PostMapping()
  public ResponseEntity<ShoppingCartLiteResponse> createShoppingCart() {

    return ResponseEntity.ok(service.createShoppingCart());
  }

}
