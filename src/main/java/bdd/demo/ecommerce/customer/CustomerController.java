package bdd.demo.ecommerce.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Optional;

import static bdd.demo.ecommerce.customer.Constants.*;

@RestController
@RequestMapping(path = APIPATH_CUSTOMERS)
@Slf4j
@Tag(name = CUSTOMER_TAG, description = CUSTOMER_API_DESC)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully")
    })
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

    @Operation(summary = "Get customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found customer with the specified id"),
            @ApiResponse(responseCode = "400", description = "Invalid customer id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> getById(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.getById(id);
        return ResponseEntity.of(customer);
    }

    @Operation(summary = "Update an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid customer id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customer = customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Delete an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully", content = @Content)
    })
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(EntityNotFoundException ex) {
        //log.info("Handled: IllegalArgumentException", ex);
        //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.notFound().build();
    }
}
