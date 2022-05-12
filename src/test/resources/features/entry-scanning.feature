Feature: entry scanning

  Background: An exchange registered user inputs all necessary strategy data and saves the strategy

  Scenario: User sees the cbot successfully find an entry position
    Given user registers exchange
    And user selects saved strategy and creates trade
    And trade status is entry searching
    When potential entry point is found
    Then user will see trade status change to entry found