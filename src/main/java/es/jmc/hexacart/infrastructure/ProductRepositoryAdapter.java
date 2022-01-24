package es.jmc.hexacart.infrastructure;

import es.jmc.hexacart.domain.Product;
import es.jmc.hexacart.domain.port.product.ProductFull;
import es.jmc.hexacart.domain.port.product.ProductLite;
import es.jmc.hexacart.domain.port.product.ProductRepository;
import es.jmc.hexacart.infrastructure.model.ProductData;
import es.jmc.hexacart.infrastructure.repository.ProductJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRepositoryAdapter implements ProductRepository {

  private final ProductJpaRepository jpa;

  @Override
  public List<ProductFull> findAll() {
    return jpa.findAll()
        .stream()
        .map(ProductRepositoryAdapter::map)
        .toList();
  }

  @Override
  public ProductFull findById(long id) {

    return jpa.findById(id)
        .map(ProductRepositoryAdapter::map)
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public ProductFull save(ProductLite newProduct) {

    var data = jpa.save(map(newProduct));

    return map(data);
  }

  @Override
  public void remove(long id) {
    jpa.deleteById(id);
  }

  @Override
  public ProductFull updateStockByProductId(long id, int stock) {

    var product = jpa.findById(id);

    product.ifPresent(productData -> {
      productData.setStock(stock);
      jpa.save(productData);
    });

    return product.map(ProductRepositoryAdapter::map).orElseThrow();
  }

  static ProductFull map(ProductData entity) {

    log.info("ProductData ==> ProductFull");

    return new ProductFull(
        entity.getId(),
        entity.getBrand(),
        entity.getName(),
        entity.getStock());
  }

  static ProductData map(ProductLite dto) {

    log.info("ProductLite ==> ProductData");

    var data = new ProductData();
    data.setBrand(dto.brand());
    data.setName(dto.name());
    data.setStock(dto.stock());

    return data;
  }

}
