package bdd.demo.ecommerce.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static bdd.demo.ecommerce.customer.Constants.APIPATH_CUSTOMERS;

@RestController
@RequestMapping(path = APIPATH_CUSTOMERS)
@Slf4j
public class CustomerController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> create(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
        Long id = 1L;
        log.info("Received customer: {}", customer.toString());
        customer.setId(id);
        final URI uri = uriComponentsBuilder.path(APIPATH_CUSTOMERS + "/{id}")
                .build(id);
        return ResponseEntity.created(uri)
                //.build()
                .body(customer)
                ;
    }
}
