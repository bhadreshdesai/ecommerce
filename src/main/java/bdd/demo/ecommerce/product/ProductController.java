package bdd.demo.ecommerce.product;

import bdd.demo.ecommerce.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static bdd.demo.ecommerce.customer.Constants.*;
import static bdd.demo.ecommerce.product.Constants.*;

@RestController
@RequestMapping(APIPATH_PRODUCTS)
@RequiredArgsConstructor
@Slf4j
@Tag(name = PRODUCT_TAG, description = PRODUCT_API_DESC)
public class ProductController extends BaseController<Product, String, ProductRepository> {
}
