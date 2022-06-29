package bdd.demo.ecommerce.customer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepDefinitions {
    @Given("A customer payload of")
    public void a_customer_payload_of(String payload) {
        log.info("Payload: {}", payload);
    }
    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String url) {
        log.info("API url: {}", url);
    }
    @Then("I get a response code of {int}")
    public void i_get_a_response_code_of(Integer responseCode) {
        log.info("ResponseCode: {}", responseCode);
    }
}
