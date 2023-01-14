package bdd.demo.ecommerce.product;

import bdd.demo.ecommerce.common.ScenarioState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class StepDefinitions {

    @LocalServerPort
    private int port;

    @Autowired
    private ScenarioState state;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @And("I have the following products in the system")
    public void iHaveTheFollowingProductsInTheSystem(DataTable dataTable) throws JsonProcessingException {
        log.info("Executing iHaveTheFollowingProductsInTheSystem");
        List<Map<String, String>> products = dataTable.asMaps();
        for (Map<String, String> product : products) {
            String name = product.get("name");
            String description = product.get("description");
            BigDecimal price = new BigDecimal(product.get("price"));
            String idRef = product.get("idRef");
            createProduct(name, description, price, idRef);
        }
    }

    private void createProduct(String name, String description, BigDecimal price, String idRef) throws JsonProcessingException {
        // TODO: Find better way to create the json payload
        /*
        String payload = MessageFormat.format("""
    '{'
      "name": "{0}",
      "description": "{1}",
      "price": "{2}",
      "gender": "{3}"
    '}'
    """, name, description, price, gender);
    */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode customer = mapper.createObjectNode();
        customer.put("name", name);
        customer.put("description", description);
        customer.put("price", price);
        String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);

        //String payload = javax.json.Json
        //@formatter:off
        final String POST_API_PATH = "/api/products";
        String id = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(POST_API_PATH)
                .then()
                    .extract()
                        .jsonPath()
                        .getString("id");
        //@formatter:on
        log.info("idRef: {}/{}", idRef, id);
        state.getMapIdRefs().put(idRef, id);
    }
}
