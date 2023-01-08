package bdd.demo.ecommerce.order;

import bdd.demo.ecommerce.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService extends BaseService<Order, Long, OrderRepository> {
   public void placeOrder(OrderRequest orderRequest) {
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());
      List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapTpDto).toList();
      order.setOrderLineItems(orderLineItems);
      this.repository.save(order);
   }

   private OrderLineItems mapTpDto(OrderLineItemsDto orderLineItemsDto) {
      OrderLineItems orderLineItems = new OrderLineItems();
      orderLineItems.setPrice(orderLineItemsDto.getPrice());
      orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
      orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
      return orderLineItems;
   }
}
