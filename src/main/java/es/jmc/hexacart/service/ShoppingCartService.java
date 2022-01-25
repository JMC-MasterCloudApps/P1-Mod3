package es.jmc.hexacart.service;

import static java.util.stream.Collectors.toSet;

import es.jmc.hexacart.controller.ShoppingCartFullResponse;
import es.jmc.hexacart.controller.ShoppingCartLiteResponse;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartFull;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartLite;
import es.jmc.hexacart.domain.port.shoppingcart.ShoppingCartUseCase;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartService {

  private final ShoppingCartUseCase useCase;

  public ShoppingCartLiteResponse createShoppingCart() {
    return map(useCase.create());
  }

  public ShoppingCartFullResponse getShoppingCart(long id) {

    ShoppingCartFull response = useCase.get(id);
    return map(response);
  }

  public void deleteShoppingCart(long id) {

    useCase.delete(id);
  }

  public void completeShoppingCart(long id) {

    useCase.complete(id);
  }

  public ShoppingCartFullResponse addProductToShoppingCart(long id, long productId, int quantity) {

    return map(useCase.addProduct(id, productId, quantity));
  }

  private static ShoppingCartLiteResponse map(ShoppingCartLite dto) {

    return new ShoppingCartLiteResponse(dto.id(), dto.status().name());
  }

  private static ShoppingCartFullResponse map(ShoppingCartFull dto) {

    var products = dto.products().entrySet().stream()
        .map(entry -> ProductService.map(entry.getKey()))
        .collect(toSet());

    return new ShoppingCartFullResponse(
        dto.id(),
        dto.status(),
        products);
  }

}
