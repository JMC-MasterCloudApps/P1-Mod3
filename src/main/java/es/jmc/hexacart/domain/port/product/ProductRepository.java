package es.jmc.hexacart.domain.port.product;

import java.util.List;

public interface ProductRepository {

  List<ProductFull> findAll();

  ProductFull findById(long id);

  ProductFull save(ProductLite newProduct);

  void remove(long id);

  ProductFull updateStockByProductId(long id, int stock);
}
