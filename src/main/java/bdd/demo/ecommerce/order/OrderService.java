package bdd.demo.ecommerce.order;

import bdd.demo.ecommerce.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<Order, Long, OrderRepository> {
}
