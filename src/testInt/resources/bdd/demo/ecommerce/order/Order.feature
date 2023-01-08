Feature: Order
  In order to test order placement
  I want to make sure I can place order using REST API

  Background: Order API base URL
    Given a base URL of "http://localhost" with random port

  Scenario Outline: Create order <name>
    Given a payload of
    """
    {
      "orderLineItemsDtoList": [
        {
          "skuCode": "<skuCode>",
          "price": <price>,
          "quantity": <quantity>
        }
      ]
    }
    """
    When I send a POST request to "/api/orders/placeOrder"
    Then I get a response code of 201

    Examples: Create product examples
      | skuCode  | price  | quantity |
      | iPhone13 | 1300.0 | 3        |