package es.jmc.hexacart.infrastructure.model;

import es.jmc.hexacart.domain.ShoppingCart;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ShoppingCarts")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  @Enumerated(EnumType.STRING)
  private ShoppingCart.CartStatus status;

  @Column(name="quantity")
  @MapKeyColumn(name="productId")
  @ElementCollection
  @CollectionTable(
      name="quantity_products",
      joinColumns = {@JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")})
  Map<Long, Integer> products = new HashMap<>(); // maps from attribute name to value

}
