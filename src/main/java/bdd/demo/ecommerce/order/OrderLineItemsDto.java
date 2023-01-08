package bdd.demo.ecommerce.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    @Schema(description = "order id")
    private Long id;
    @Schema(description = "sku code of the product", required = true, example = "iphone_13")
    private String skuCode;
    @Schema(description = "products price", required = true, example = "1200")
    private BigDecimal price;
    @Schema(description = "order quantity", required = true, example = "1")
    private Integer quantity;
}