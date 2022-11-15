package bdd.demo.ecommerce;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

import static bdd.demo.ecommerce.Constants.MONGODB_42_IMAGE;
import static bdd.demo.ecommerce.Constants.MYSQL_57_IMAGE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testInt")
@Slf4j
@CucumberContextConfiguration
public class CucumberSpringContextConfig {

    static MySQLContainer mySQLContainer = new MySQLContainer(MYSQL_57_IMAGE);

    static MongoDBContainer mongoDBContainer = new MongoDBContainer(MONGODB_42_IMAGE);

    // Use cucumber BeforeAll to start the containers
    // as @Testcontainers and @Container annotation does not work with cucumber
    // I suspect it is becuase junit5 BeforeAll is not triggered during cucumber run
    @BeforeAll
    public static void setup() {
        log.info("Integration Test - Before All");
        mySQLContainer.start();
        mongoDBContainer.start();
    }

    @AfterAll
    public static void cleanup() {
        log.info("Integration Test - After All");
        mySQLContainer.stop();
        mongoDBContainer.stop();
    }

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dymDynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dymDynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);

        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        log.info("Integration Test - Setting dynamic properties in the registry");
    }

}