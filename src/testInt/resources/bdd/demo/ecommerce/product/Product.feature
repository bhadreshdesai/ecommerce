Feature: Product
  In order to manage products
  I want to make sure the CRUD operation through REST API works fine

  Background: Product API base URL
    Given a base URL of "http://localhost" with random port

  Scenario Outline: Create product <name>
    Given a payload of
    """
    {
      "name": "<name>",
      "description": "<description>",
      "price": <price>
    }
    """
    When I send a POST request to "/api/products"
    Then I get a response code of 201
    And a location header with id

    Examples: Create product examples
      | name     | description     | price  |
      | iPhone13 | Apple iPhone 13 | 1300.0 |

  Scenario Outline: Update a product
    Given I have the following products in the system
      | name | description | price  | idRef  |
      | S21  | Samsung S21 | 1200.0 | idref1 |
    And a payload of
      """
      {
      "name": "<name>",
      "description": "<description>",
      "price": <price>
    }
      """
    When I send a PUT request to "/api/products" with id ref of "<idRef>"
    Then I get a response code of 200
    And body contains "name" as "<name>"
    And body contains "description" as "<description>"
    And body contains price as <price>

    Examples: Update product examples
      | name     | description     | price  | idRef  |
      | iPhone13 | Apple iPhone 13 | 1350.0 | idref1 |