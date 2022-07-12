package bdd.demo.ecommerce.customer;

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
import java.util.Optional;

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

    @Given("a base URL of {string} with random port")
    public void aBaseURLOfWithRandomPort(String baseUri) {
        log.info("Using database url: {}", databaseUrl);
        log.info("Setting the base url to {}", baseUri);
        RestAssured.baseURI = baseUri;
        RestAssured.port = port;
    }

    @Given("a customer payload of")
    public void aCustomerPayloadOf(String payload) {
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(payload);
        state.setRequestSpecification(requestSpecification);
        state.setPayload(payload);
        log.info("a_customer_payload_of.state, payload: {}, {}", state.toString(), state.getPayload());
    }

    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String requestPath) {
        state.setRequestPath(requestPath);
        Response response = state.getRequestSpecification()
                .when()
                .post(requestPath);
        state.setResponse(response);
        log.info("POST request path: {}", requestPath);
        log.info("POST request payload: {}, {}", state.toString(), state.getPayload());
    }

    @When("I send a PUT request to {string} with id ref of {string}")
    public void iSendAPUTRequestToWithIdOf(String putUrl, String idRef) {
        // TODO: validate incoming idRef
        log.info("idRef: {}", idRef);
        //state.getMapIdRefs().forEach((k, v) -> log.info((k + ":" + v)));

        // Pass in a string id ref for e.g. idRef1 which will exist in the map or pass in actual id should be numeric
        Long id = state.getMapIdRefs().get(idRef);
        if (id == null) id = Long.valueOf(idRef);

        log.info("id from map: {}", id);

        putUrl = MessageFormat.format("{0}/{1}",putUrl, id);
        Response response = state.getRequestSpecification()
                .when()
                .put(putUrl);
        state.setResponse(response);
        log.info("PUT request path: {}", putUrl);
        log.info("PUT request payload: {}, {}", state.toString(), state.getPayload());
    }

    @When("I send a DELETE request to {string} with id of {string}")
    public void iSendADELETERequestToWithIdOf(String deleteUrl, String idRef) {
        // TODO: validate incoming idRef
        Long id = state.getMapIdRefs().get(idRef);
        deleteUrl = MessageFormat.format("{0}/{1}",deleteUrl, id);
        log.info("DELETE request path: {}", deleteUrl);
        Response response = given()
                .when()
                .delete(deleteUrl);
        state.setResponse(response);
    }

    @When("I send a GET request to {string} with id of {string}")
    public void iSendAGETRequestToWithIdOf(String getUrl, String idRef) {
        // TODO: validate incoming idRef
        Long id = state.getMapIdRefs().get(idRef);
        getUrl = MessageFormat.format("{0}/{1}",getUrl, id);
        log.info("GET request path: {}", getUrl);
        Response response = given()
                .when()
                .get(getUrl);
        state.setResponse(response);
    }

    @Then("I get a response code of {int}")
    public void iGetAResponseCodeOf(Integer expectedResponseCode) {
        state.getResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(expectedResponseCode);
    }

    @And("a location header with customer id")
    public void aLocationHeaderEntryWithCustomerId() {
        Long id = state.getResponse().then().extract().jsonPath().getLong("id");
        log.info("Customer id: {}", id);
        String customerPath = MessageFormat.format("{0}/{1}", state.getRequestPath(), id);
        log.info("Location endsWith: {}", customerPath);
        state.getResponse()
                .then()
                .assertThat()
                .header("Location", endsWith(customerPath));
    }

    @And("body contains {string} as {string}")
    public void bodyContainsAttributeValueAs(String attribute, String value) {
        state.getResponse()
                .then()
                .assertThat()
                .body(attribute, equalTo(value));
    }

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
        state.getMapIdRefs().put(idRef, id);
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
