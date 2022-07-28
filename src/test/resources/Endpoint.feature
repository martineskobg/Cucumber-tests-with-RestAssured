@endpoint @endpoints
Feature: Check the status code for all endpoints

  Background: I have OpenAPi link
    Given Api url "https://api.publicapis.org/"

  @endpointStatusCode
  Scenario Outline: Check if the status code of all endpoints is 200
    Given endpoint is: <endpoint>
    When get Response Status
    Then Verify Status code is 200
    Examples:
      | endpoint   |
      | health     |
      | categories |
      | random     |
      | entries    |



