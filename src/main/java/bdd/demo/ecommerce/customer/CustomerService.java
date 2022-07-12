package bdd.demo.ecommerce.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer update(Long id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(!existingCustomer.isPresent()) {
            throw new EntityNotFoundException();
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }
}
