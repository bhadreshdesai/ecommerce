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

  Scenario: Try to update a non-existent product
    Given a payload of
      """
      {
        "name": "doesNotExist",
        "description": "Does not exist",
        "price": 0
      }
      """
    When I send a PUT request to "/api/products" with id of "999"
    Then I get a response code of 404

  Scenario: Try to get a non-existent customer
    When I send a GET request to "/api/products" with id of "999"
    Then I get a response code of 404

  Scenario: Delete a product
    Given I have the following products in the system
      | name     | description     | price  | idRef  |
      | iPhone13 | Apple iPhone 13 | 1350.0 | idref2 |
    When I send a DELETE request to "/api/products" with id ref of "idref2"
    Then I get a response code of 204