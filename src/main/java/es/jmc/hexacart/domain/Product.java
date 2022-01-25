package es.jmc.hexacart.domain;

import es.jmc.hexacart.domain.port.product.ProductFull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Product {

  private long id;
  private String brand;
  private String name;
  private int stock;

  public Product(ProductFull dto) {
    id = dto.id();
    brand = dto.brand();
    name = dto.name();
    stock = dto.stock();
  }
}
