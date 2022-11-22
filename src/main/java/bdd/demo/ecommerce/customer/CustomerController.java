package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseController;
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
public class CustomerController extends BaseController<Customer, Long, CustomerRepository> {
}
