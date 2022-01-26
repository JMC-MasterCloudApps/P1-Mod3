package es.jmc.hexacart.service;

import es.jmc.hexacart.controller.NewProductRequest;
import es.jmc.hexacart.controller.ProductFullResponse;
import es.jmc.hexacart.domain.port.product.ProductFull;
import es.jmc.hexacart.domain.port.product.ProductLite;
import es.jmc.hexacart.domain.port.product.ProductUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

  private final ProductUseCase useCase;

  public List<ProductFullResponse> getProducts() {

    List<ProductFull> products = useCase.getProducts();

    return products.stream()
        .map(ProductService::map)
        .toList();
  }

  public ProductFullResponse getProduct(long id) {

    return map(useCase.getProduct(id));
  }

  public void deleteProduct(long id) {

    useCase.removeProduct(id);
  }

  public ProductFullResponse addProduct(NewProductRequest newProduct) {

    var result = useCase.addProduct(map(newProduct));

    return map(result);
  }

  public ProductFullResponse updateProductStock(long id, int quantity) {

    return map(useCase.updateProductStock(id, quantity));
  }

  static ProductFullResponse map(ProductFull product) {

    return new ProductFullResponse(
        product.id(),
        product.brand(),
        product.name(),
        product.stock());
  }

  private static ProductLite map(NewProductRequest productRequest) {

    return new ProductLite(
        productRequest.brand(),
        productRequest.name(),
        productRequest.stock());
  }
}
