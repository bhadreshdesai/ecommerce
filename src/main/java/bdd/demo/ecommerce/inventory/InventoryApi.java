package bdd.demo.ecommerce.inventory;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface InventoryApi {
   @Operation(summary = "Get inventory status by skuCode")
   @GetMapping("/{sku-code}")
   @ResponseStatus(HttpStatus.OK)
   public boolean isInStock(@PathVariable("sku-code") String skuCode);
}
