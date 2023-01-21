package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer, Long, CustomerRepository> {
}
