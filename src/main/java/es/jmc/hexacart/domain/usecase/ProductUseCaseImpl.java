package es.jmc.hexacart.domain.usecase;

import es.jmc.hexacart.domain.port.ProductFull;
import es.jmc.hexacart.domain.port.ProductLite;
import es.jmc.hexacart.domain.port.ProductRepository;
import es.jmc.hexacart.domain.port.ProductUseCase;
import java.util.List;

public class ProductUseCaseImpl implements ProductUseCase {

  private final ProductRepository repository;

  public ProductUseCaseImpl(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<ProductFull> getProducts() {
    return repository.findAll();
  }

  @Override
  public ProductFull getProduct(long id) {
    return repository.findById(id);
  }

  @Override
  public ProductFull addProduct(ProductLite newProduct) {
    return repository.save(newProduct);
  }

  @Override
  public void removeProduct(long id) {
    repository.remove(id);
  }

  @Override
  public ProductFull updateProductStock(long id, int stock) {
    return repository.updateStockByProductId(id, stock);
  }
}
