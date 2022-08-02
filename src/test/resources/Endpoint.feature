@endpoint @endpoints
Feature: Check the status code for all endpoints

  @endpointStatusCode
  Scenario Outline: Status code 200 for endpoints
    Given I have API url "https://api.publicapis.org/"
    When I call <endpointName> endpoint
    Then I verify that the status code is 200
    Examples:
      | endpointName |
      | health       |
      | categories   |
      | random       |
      | entries      |



