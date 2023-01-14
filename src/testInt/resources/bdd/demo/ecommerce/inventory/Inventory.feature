Feature: Inventory
  In order to test inventory checking
  I want to make sure I can check product inventory status using REST API

  Background: Inventory API base URL
    Given a base URL of "http://localhost" with random port

  Scenario Outline: Check inventory status of  <skuCode>
    Given I have the following products in my inventory
      | skuCode  | quantity |
      | iPhone13 | 1300     |
    When I send a GET request to "/api/inventory" with id of "<skuCode>"
    Then I get a response code of 200
    Examples: Create product examples
      | skuCode  |
      | iPhone13 |