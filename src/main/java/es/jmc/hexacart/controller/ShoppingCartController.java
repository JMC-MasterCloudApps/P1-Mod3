package es.jmc.hexacart.controller;

import es.jmc.hexacart.service.ShoppingCartService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("{id}")
  public ResponseEntity<ShoppingCartFullResponse> getShoppingCart(@PathVariable long id) {

    ShoppingCartFullResponse response = service.getShoppingCart(id);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity deleteShoppingCart(@PathVariable long id) {

    service.deleteShoppingCart(id);

    return ResponseEntity.ok().build();
  }

}
