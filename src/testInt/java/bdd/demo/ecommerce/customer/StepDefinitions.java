package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.common.ScenarioState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class StepDefinitions {

    @LocalServerPort
    private int port;

    @Autowired
    private ScenarioState state;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    private void createCustomer(String firstName, String lastName, String dateOfBirth, String gender, String idRef) throws JsonProcessingException {
        // TODO: Find better way to create the json payload
        /*
        String payload = MessageFormat.format("""
    '{'
      "firstName": "{0}",
      "lastName": "{1}",
      "dateOfBirth": "{2}",
      "gender": "{3}"
    '}'
    """, firstName, lastName, dateOfBirth, gender);
    */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode customer = mapper.createObjectNode();
        customer.put("firstName", firstName);
        customer.put("lastName", lastName);
        customer.put("dateOfBirth", dateOfBirth);
        customer.put("gender", gender);
        String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);

        //String payload = javax.json.Json
        //@formatter:off
        Long id = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post("/api/customers")
                .then()
                    .extract()
                        .jsonPath()
                        .getLong("id");
        //@formatter:on
        log.info("idRef: {}/{}", idRef, id);
        state.getMapIdRefs().put(idRef, String.valueOf(id));
    }

    @And("I have the following customers in the system")
    public void iHaveTheFollowingCustomersInTheSystem(DataTable dataTable) throws JsonProcessingException {
        log.info("Executing iHaveTheFollowingCustomersInTheSystem");
        List<Map<String, String>> customers = dataTable.asMaps();
        for (Map<String, String> customer : customers) {
            String firstName = customer.get("firstName");
            String lastName = customer.get("lastName");
            String dateOfBirth = customer.get("dateOfBirth");
            String gender = customer.get("gender");
            String idRef = customer.get("idRef");
            createCustomer(firstName, lastName, dateOfBirth, gender, idRef);
        }
    }
}
