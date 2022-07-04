package bdd.demo.ecommerce.customer;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Getter
@Setter
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
// You can use state bean so it can be shared by steps defined in different files
public class ScenarioState {
    private RequestSpecification requestSpecification;
    private Response response;
    private String payload;
}
