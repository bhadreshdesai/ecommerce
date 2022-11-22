package bdd.demo.ecommerce.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {
}