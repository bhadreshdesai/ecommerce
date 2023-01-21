package bdd.demo.ecommerce.product;

import bdd.demo.ecommerce.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class Product extends BaseEntity<Long> {
    @Schema(description = "Product name", example = "iPhone13")
    private String name;
    @Schema(description = "Product description", example = "iPhone13 - Red Colour")
    private String description;
    @Schema(description = "Price", example = "1300")
    private BigDecimal price;
}
