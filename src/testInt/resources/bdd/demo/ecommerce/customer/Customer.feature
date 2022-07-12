Feature: Customer
  In order to manage customers
  I want to make sure the CRUD operations through REST API works fine

  Background: Customer API base URL
    Given a base URL of "http://localhost" with random port

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
    And a location header with customer id
    And body contains "firstName" as "<firstName>"
    And body contains "lastName" as "<lastName>"
    And body contains "dateOfBirth" as "<dateOfBirth>"
    And body contains "gender" as "<gender>"

    Examples: Create customer examples
      | firstName | lastName | dateOfBirth | gender |
      | John      | Doe      | 1971-03-31  | M      |
      | Jane      | Doe      | 1971-04-01  | F      |

  Scenario Outline: Update a customer
    Given I have the following customers in the system
      | firstName | lastName | dateOfBirth | gender | idRef  |
      | Update    | Customer | 1971-03-31  | M      | idref1 |
    And a customer payload of
      """
      {
        "firstName": "<firstName>",
        "lastName": "<lastName>",
        "dateOfBirth": "<dateOfBirth>",
        "gender": "<gender>"
      }
      """
    When I send a PUT request to "/api/customers" with id ref of "<idRef>"
    Then I get a response code of 200
    And body contains "firstName" as "<firstName>"
    And body contains "lastName" as "<lastName>"
    And body contains "dateOfBirth" as "<dateOfBirth>"
    And body contains "gender" as "<gender>"

    Examples: Update customer examples
      | firstName | lastName | dateOfBirth | gender | idRef  |
      | Update    | LastName | 1971-03-31  | M      | idref1 |

  Scenario: Delete a customer
    Given I have the following customers in the system
      | firstName | lastName | dateOfBirth | gender | idRef  |
      | Delete    | Customer | 1971-04-01  | F      | idref2 |
    When I send a DELETE request to "/api/customers" with id of "idref2"
    Then I get a response code of 204

  Scenario: Get a customer
    Given I have the following customers in the system
      | firstName | lastName | dateOfBirth | gender | idRef  |
      | Get       | Customer | 1971-04-01  | F      | idref3 |
    When I send a GET request to "/api/customers" with id of "idref3"
    Then I get a response code of 200
    And body contains "firstName" as "Get"
    And body contains "lastName" as "Customer"
    And body contains "dateOfBirth" as "1971-04-01"
    And body contains "gender" as "F"

  Scenario: Try to update a non-existent customer
    #Given a customer with id "999" does not exist in the system
    Given a customer payload of
      """
      {
        "firstName": "Nonexistant",
        "lastName": "Customer",
        "dateOfBirth": "1971-04-01",
        "gender": "F"
      }
      """
    When I send a PUT request to "/api/customers" with id ref of "999"
    Then I get a response code of 404