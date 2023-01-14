package bdd.demo.ecommerce.inventory;

import bdd.demo.ecommerce.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Inventory extends BaseEntity<Long> {
   private String skuCode;
   private Integer quantity;
}
