Feature: Customer
  In order to manage customers
  I want to make sure the CRUD operations through REST API works fine

  Scenario Outline: Create customer <firstName> <lastName>
    Given A customer payload of
    """
    {
      "firstName": "<firstName>",
      "lastName": "<lastName>",
      "dateOfBirth": "<dateOfBirth>",
      "gender": "<gender>"
    }
    """
    When I send a POST request to "/api/customer"
    Then I get a response code of 201

    Examples:
    | firstName | lastName | dateOfBirth | gender |
    | John      | Doe      | 1971-03-31  | M      |
    | Jane      | Doe      | 1971-04-01  | F      |

