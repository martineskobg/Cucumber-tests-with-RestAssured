@endpoint @entries
Feature: Test Entries API

  Background:
    Given  Api base url for entries "https://api.publicapis.org/entries"

  @entriesCount
  Scenario: Check the count of the entries
    Given Expected count of all entries is 1425
    When Get the actual count
    Then Verify that expected count is equal to actual

  @entryContent
  Scenario Outline: Check if all entries contains the same parameters
    Given Entry parameter <parameter>
    When Get all entries
    Then Verify that the entry contains the given parameter
    Examples:
      | parameter   |
      | API         |
      | Description |
      | Auth        |
      | HTTPS       |
      | Cors        |
      | Link        |
      | Category    |

  @httpsIsFalse
  Scenario: Check the count of all entries with parameter HTTPS equal to false
    Given Count of entries with parameter HTTPS false is 92
    When Get all entries with parameter HTTPS equal to false
    Then Verify expected and actual count