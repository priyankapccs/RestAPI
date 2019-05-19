Feature: This is the set of test about Create Feature

  # Test to validate the Create feature
  Scenario Outline: Checking the functionality of create feature
    Given The service is up
    And Get valid ids and save the response
    When Hit "<apiType>" api to "<functionality>" data
    Then The api "<functionality>" is successful
    Examples:
      | apiType | functionality |
      | POST    | create        |
      | GET     | view          |
      | PUT     | update        |
      | DELETE  | delete        |
