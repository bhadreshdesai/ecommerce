package bdd.demo.ecommerce.order;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface OrderApi {
   @Operation(summary = "Place an order")
   @PostMapping("/placeOrder")
   @ResponseStatus(HttpStatus.CREATED)
   String placeOrder(@RequestBody OrderRequest orderRequest);
}
