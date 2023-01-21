package bdd.demo.ecommerce.product;

import bdd.demo.ecommerce.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.ecommerce.product.Constants.*;

@RestController
@RequestMapping(APIPATH_PRODUCTS)
@RequiredArgsConstructor
@Slf4j
@Tag(name = PRODUCT_TAG, description = PRODUCT_API_DESC)
public class ProductController extends BaseController<Product, Long, ProductRepository> {
}
