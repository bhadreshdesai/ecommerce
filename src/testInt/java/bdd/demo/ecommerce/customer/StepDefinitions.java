package bdd.demo.ecommerce.customer;

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
    public void a_base_url_of(String baseUri) {
        log.info("Using database url: {}", databaseUrl);
        log.info("Setting the base url to {}", baseUri);
        RestAssured.baseURI = baseUri;
        RestAssured.port = port;
    }

    @Given("a customer payload of")
    public void a_customer_payload_of(String payload) {
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(payload);
        state.setRequestSpecification(requestSpecification);
        state.setPayload(payload);
        log.info("a_customer_payload_of.state, payload: {}, {}", state.toString(), state.getPayload());
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String requestPath) {
        state.setRequestPath(requestPath);
        Response response = state.getRequestSpecification()
                .when()
                .post(requestPath);
        state.setResponse(response);
        log.info("API requestPath: {}", requestPath);
        log.info("i_send_a_post_request_to.state, payload: {}, {}", state.toString(), state.getPayload());
    }

    @Then("I get a response code of {int}")
    public void i_get_a_response_code_of(Integer expectedResponseCode) {
        state.getResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(expectedResponseCode);
    }

    @And("a location header with customer id")
    public void aLocationHeaderEntryWithCustomerId() {
        Integer id = state.getResponse().then().extract().path("id");
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
}
