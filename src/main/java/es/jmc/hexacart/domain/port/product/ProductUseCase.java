package es.jmc.hexacart.domain.port.product;

import java.util.List;

public interface ProductUseCase {

  List<ProductFull> getProducts();

  ProductFull getProduct(long id);

  ProductFull addProduct(ProductLite newProduct);

  void removeProduct(long id);

  ProductFull updateProductStock(long id, int stock);
}
