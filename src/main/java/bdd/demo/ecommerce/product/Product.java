package bdd.demo.ecommerce.product;

import bdd.demo.ecommerce.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.math.BigDecimal;

@AllArgsConstructor
@Document(value = "product")
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Product extends BaseEntity<String> {
    private String name;
    private String description;
    private BigDecimal price;
}
