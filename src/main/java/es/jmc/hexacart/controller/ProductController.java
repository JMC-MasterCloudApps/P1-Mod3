package es.jmc.hexacart.controller;

import es.jmc.hexacart.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

  private static final String ID = "{id}";
  private static final String UPDATE_STOCK_PATH = "/" + ID + "/stock/{quantity}";

  private final ProductService service;

  @GetMapping()
  public List<ProductFullResponse> getProducts() {

    return service.getProducts();
  }

  @GetMapping(ID)
  public ResponseEntity<ProductFullResponse> getProduct(@PathVariable long id) {

    var product = service.getProduct(id);

    return ResponseEntity.ok(product);
  }

  @DeleteMapping(ID)
  public ResponseEntity<Void> deleteProduct(@PathVariable long id) {

    service.deleteProduct(id);

    return ResponseEntity.ok().build();
  }

  @PostMapping()
  public ResponseEntity<ProductFullResponse> addProduct(@RequestBody NewProductRequest newProduct) {

    var result = service.addProduct(newProduct);

    return ResponseEntity.ok(result);
  }

  @PutMapping(UPDATE_STOCK_PATH)
  public ResponseEntity<ProductFullResponse> updateStock(@PathVariable long id, @PathVariable int quantity) {

    var result = service.updateProductStock(id, quantity);

    return ResponseEntity.ok(result);
  }
}
