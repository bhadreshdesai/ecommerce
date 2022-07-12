package bdd.demo.ecommerce.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Optional;

import static bdd.demo.ecommerce.customer.Constants.APIPATH_CUSTOMERS;

@RestController
@RequestMapping(path = APIPATH_CUSTOMERS)
@Slf4j
public class CustomerController {

    @Autowired CustomerService customerService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> create(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
        log.info("Received customer: {}", customer.toString());
        customer = customerService.create(customer);
        final URI uri = uriComponentsBuilder.path(APIPATH_CUSTOMERS + "/{id}")
                .build(customer.getId());
        return ResponseEntity.created(uri)
                .body(customer)
                ;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> getById(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.getById(id);
        return ResponseEntity.of(customer);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customer = customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(EntityNotFoundException ex) {
        //log.info("Handled: IllegalArgumentException", ex);
        //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
