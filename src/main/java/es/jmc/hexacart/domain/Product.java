package es.jmc.hexacart.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Product {

  private long id;
  private String brand;
  private String name;
  private int stock;
}
