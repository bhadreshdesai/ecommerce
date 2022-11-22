package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomerService extends BaseService<Customer, Long, CustomerRepository> {
}
