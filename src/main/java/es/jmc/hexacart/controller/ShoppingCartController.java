package es.jmc.hexacart.controller;

import es.jmc.hexacart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingcarts/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartController {

  private static final String ID = "{id}";
  private static final String PROD_ID = "prod_id";
  private static final String NUM_OF_PROD = "prod_quantity";
  private static final String PRODUCT_CART_PATH = ID + "/product/{" + PROD_ID + "}";
  private static final String NUM_OF_PRODUCT_CART_PATH = ID + "/product/{" + PROD_ID + "}/quantity/{" + NUM_OF_PROD + "}";

  private final ShoppingCartService service;

  @PostMapping()
  public ResponseEntity<ShoppingCartLiteResponse> createShoppingCart() {

    return ResponseEntity.ok(service.createShoppingCart());
  }

  @GetMapping(ID)
  public ResponseEntity<ShoppingCartFullResponse> getShoppingCart(@PathVariable long id) {

    ShoppingCartFullResponse response = service.getShoppingCart(id);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping(ID)
  public ResponseEntity<Void> deleteShoppingCart(@PathVariable long id) {

    service.deleteShoppingCart(id);

    return ResponseEntity.ok().build();
  }

  @PatchMapping(ID)
  public ResponseEntity<Void> completeShoppingCart(@PathVariable long id) {

    service.completeShoppingCart(id);

    return ResponseEntity.ok().build();
  }


  @PostMapping(NUM_OF_PRODUCT_CART_PATH)
  public ResponseEntity<ShoppingCartFullResponse> addProductToShoppingCart(
      @PathVariable long id,
      @PathVariable(PROD_ID) long productId,
      @PathVariable(NUM_OF_PROD) int quantity) {

    ShoppingCartFullResponse result = service.addProductToShoppingCart(id, productId, quantity);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping(PRODUCT_CART_PATH)
  public ResponseEntity<Void> removeProductFromShoppingCart(
      @PathVariable long id,
      @PathVariable(PROD_ID) long productId) {

    service.removeProductFromShoppingCart(id, productId);

    return ResponseEntity.ok().build();
  }


}
