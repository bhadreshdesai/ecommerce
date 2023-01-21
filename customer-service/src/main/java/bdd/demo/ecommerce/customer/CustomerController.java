package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.ecommerce.customer.Constants.*;

@RestController
@RequestMapping(path = APIPATH_CUSTOMERS)
@Slf4j
@Tag(name = CUSTOMER_TAG, description = CUSTOMER_API_DESC)
public class CustomerController extends BaseController<Customer, Long, CustomerRepository> {
}
