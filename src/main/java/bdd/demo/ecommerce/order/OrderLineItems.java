package bdd.demo.ecommerce.order;

import bdd.demo.ecommerce.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.math.BigDecimal;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class OrderLineItems extends BaseEntity<Long> {
   private String skuCode;
   private BigDecimal price;
   private Integer quantity;
}
