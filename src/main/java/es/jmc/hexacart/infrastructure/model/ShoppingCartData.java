package es.jmc.hexacart.infrastructure.model;

import static javax.persistence.FetchType.LAZY;

import es.jmc.hexacart.domain.ShoppingCart;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @OneToMany(fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProductData> products = new HashSet<>();
}
