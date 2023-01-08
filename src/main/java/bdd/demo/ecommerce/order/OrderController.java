package bdd.demo.ecommerce.order;

import bdd.demo.ecommerce.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.ecommerce.order.Constants.*;

@RestController
@RequestMapping(path = APIPATH_ORDERS)
@Slf4j
@Tag(name = ORDER_TAG, description = ORDER_API_DESC)
public class OrderController extends BaseController<Order, Long, OrderRepository> implements OrderApi {

   private OrderService getOrderService() {
      return (OrderService) this.getService();
   }

   public String placeOrder(@RequestBody OrderRequest orderRequest) {
      this.getOrderService().placeOrder(orderRequest);
      return "Order placed successfully";
   }
}
