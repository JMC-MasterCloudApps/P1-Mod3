package es.jmc.hexacart.infrastructure;

import es.jmc.hexacart.domain.port.ProductFull;
import es.jmc.hexacart.domain.port.ProductLite;
import es.jmc.hexacart.domain.port.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

  List<ProductFull> products;

  public ProductRepositoryAdapter() {
    products = new ArrayList<>();
    products.add(new ProductFull(1, "Apple", "Mac Book Pro", 234));
    products.add(new ProductFull(2, "Xiaomi", "Redmi 9 BS", 23));
    products.add(new ProductFull(3, "Samsung", "Galaxy S15", 54));
  }

  @Override
  public List<ProductFull> findAll() {
    // TODO
    return products;
  }

  @Override
  public ProductFull findById(long id) {
    // TODO
    return products.stream()
        .filter(productFull -> productFull.id() == id)
        .findAny()
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public ProductFull save(ProductLite newProduct) {

    // TODO
    var productFull = new ProductFull(products.size() + 1, newProduct.brand(), newProduct.name(), newProduct.stock());

    products.add(productFull);
    return productFull;
  }

  @Override
  public void remove(long id) {

    // TODO
    var product = findById(id);
    products.remove(product);
  }

  @Override
  public ProductFull updateStockByProductId(long id, int stock) {

    var product = findById(id);
    var updatedProduct = new ProductFull(id, product.brand(), product.name(), stock);

    products.remove(product);
    products.add(updatedProduct);

    return updatedProduct;
  }
}
