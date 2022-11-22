Feature: Product
  In order to manage products
  I want to make sure the CRUD operation through REST API works fine

  Background: Product API base URL
    Given a base URL of "http://localhost" with random port

  Scenario Outline: Create product <model>
    Given a payload of
    """
    {
      "name": "<model>",
      "description": "<description>",
      "price": <price>
    }
    """
    When I send a POST request to "/api/products"
    Then I get a response code of 201
    And a location header with id

    Examples: Create product examples
      | model     | description     | price |
      | iPhone13  | Apple iPhone 13 | 1300  |