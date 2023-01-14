package bdd.demo.ecommerce.inventory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class StepDefinitions {
   @Given("I have the following products in my inventory")
   public void iHaveTheFollowingProductsInMyInventory(DataTable dataTable) throws JsonProcessingException {
      log.info("Executing iHaveTheFollowingProductsInMyInventory");
      List<Map<String, String>> products = dataTable.asMaps();
      for (Map<String, String> product : products) {
         String skuCode = product.get("skuCode");
         Long quantity =  Long.valueOf(product.get("quantity"));
         createInventory(skuCode, quantity);
      }
   }

   private void createInventory(String skuCode, Long quantity) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode inventory = mapper.createObjectNode();
      inventory.put("skuCode", skuCode);
      inventory.put("quantity", quantity);
      String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inventory);
      //@formatter:off
      final String POST_API_PATH = "/api/inventory";
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
   }
}
