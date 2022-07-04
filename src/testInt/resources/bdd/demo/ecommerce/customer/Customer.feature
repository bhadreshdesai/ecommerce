Feature: Customer
  In order to manage customers
  I want to make sure the CRUD operations through REST API works fine

  Background: Customer API base URL
    Given a base URL of "http://localhost"

  Scenario Outline: Create customer <firstName> <lastName>
    Given a customer payload of
    """
    {
      "firstName": "<firstName>",
      "lastName": "<lastName>",
      "dateOfBirth": "<dateOfBirth>",
      "gender": "<gender>"
    }
    """
    When I send a POST request to "/api/customers"
    Then I get a response code of 201

    Examples: Example customers
    | firstName | lastName | dateOfBirth | gender |
    | John      | Doe      | 1971-03-31  | M      |
    | Jane      | Doe      | 1971-04-01  | F      |
