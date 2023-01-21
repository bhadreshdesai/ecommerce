package bdd.demo.ecommerce;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static bdd.demo.ecommerce.Constants.MYSQL_57_IMAGE;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Product Service - Spring Context Unit Test")
@Testcontainers
@Slf4j
class ProductServiceApplicationTests {
	@Container
	static MySQLContainer mySQLContainer = new MySQLContainer(MYSQL_57_IMAGE);


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dymDynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
		dymDynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);

		log.info("Setting dynamic properties in the registry");
	}
	@Test
	void contextLoads() {
		log.info("Test spring context load");
	}

}
