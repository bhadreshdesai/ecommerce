package bdd.demo.ecommerce.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.number.BigDecimalCloseTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.text.MessageFormat;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
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
        // Need this for the BigDecimal comparison to work
        RestAssured.config = newConfig().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    @Given("a payload of")
    public void aPayloadOf(String payload) {
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(payload);
        state.setRequestSpecification(requestSpecification);
        state.setPayload(payload);
        log.info("a_payload_of.state, payload: {}, {}", state.toString(), state.getPayload());
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
    public void iSendAPUTRequestToWithIdRefOf(String putUrl, String idRef) {
        // TODO: validate incoming idRef
        log.info("idRef: {}", idRef);
        //state.getMapIdRefs().forEach((k, v) -> log.info((k + ":" + v)));

        // Pass in a string id ref for e.g. idRef1 which will exist in the map or pass in actual id should be numeric
        String id = state.getMapIdRefs().get(idRef);
        if (id == null) id = idRef;
        log.info("id from map: {}", id);

        iSendAPUTRequestToWithIdOf(putUrl, id);
    }

    @When("I send a PUT request to {string} with id of {string}")
    public void iSendAPUTRequestToWithIdOf(String putUrl, String id) {
        putUrl = MessageFormat.format("{0}/{1}",putUrl, id);
        Response response = state.getRequestSpecification()
                .when()
                .put(putUrl);
        state.setResponse(response);
        log.info("PUT request path: {}", putUrl);
        log.info("PUT request payload: {}, {}", state.toString(), state.getPayload());
        log.info("PUT response: {}", response);
    }

    @When("I send a DELETE request to {string} with id ref of {string}")
    public void iSendADELETERequestToWithIdRefOf(String deleteUrl, String idRef) {
        // TODO: validate incoming idRef
        String id = state.getMapIdRefs().get(idRef);
        deleteUrl = MessageFormat.format("{0}/{1}",deleteUrl, id);
        log.info("DELETE request path: {}", deleteUrl);
        Response response = given()
                .when()
                .delete(deleteUrl);
        state.setResponse(response);
    }

    @When("I send a GET request to {string} with id ref of {string}")
    public void iSendAGETRequestToWithIdRefOf(String getUrl, String idRef) {
        // TODO: validate incoming idRef
        // Pass in a string id ref for e.g. idRef1 which will exist in the map or pass in actual id should be numeric
        String id = state.getMapIdRefs().get(idRef);
        if (id == null) id = idRef;
        iSendAGETRequestToWithIdOf(getUrl, id);
    }

    @When("I send a GET request to {string} with id of {string}")
    public void iSendAGETRequestToWithIdOf(String getUrl, String id) {
        log.info("id from map: {}", id);
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

    @And("a location header with id")
    public void aLocationHeaderEntryWithId() {
        //Long id = state.getResponse().then().extract().jsonPath().getLong("id");
        String id = state.getResponse().then().extract().jsonPath().getString("id");
        log.info("id: {}", id);
        String path = MessageFormat.format("{0}/{1}", state.getRequestPath(), id);
        log.info("Location endsWith: {}", path);
        state.getResponse()
                .then()
                .assertThat()
                .header("Location", endsWith(path));
    }

    @And("body contains {string} as {string}")
    public void bodyContainsAttributeValueAs(String attribute, String value) {
        state.getResponse()
                .then()
                .assertThat()
                .body(attribute, equalTo(value));
    }

    @And("^body contains (\\w+) as (\\d+.\\d+)$")
    public void bodyContainsAttributeValueAs(String attribute, BigDecimal expectedValue) {
        log.info("attribute: {}, value: {}", attribute, expectedValue);
        log.info("Response: {}", state.getResponse().prettyPrint());
        state.getResponse()
                .then()
                .assertThat()
                .body(attribute, BigDecimalCloseTo.closeTo(expectedValue, BigDecimal.valueOf(0.001)));
    }

}
