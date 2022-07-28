@endpoint @random
Feature: Test Random API

  Background: I have OpenAPi link
    Given Api base url for random "https://api.publicapis.org/random"

  @randomCount
  Scenario: Check the count of the random endpoint
    Given Expected value of count 1
    When Get the actual random count
    Then Verify the count
  @randomParameterCount
  Scenario: Check the count of random parameters
    Given Expected count of the random endpoint parameters is 7
    When Take the actual count of the random parameters
    Then Verify that the expected and actual count are equal

  @randomHttpsValue
  Scenario: Check the value of HTTPS parameter
    Given Parameter "HTTPS"
    When Get the value of HTTPS parameter
    Then Verify that HTTPS has value true

