@mock-brokerage
@mock-kraken
Feature: entry scanning

  Background: A registered user inputs all necessary strategy data and saves the strategy

  Scenario: User sees the cbot successfully find an entry position
    Given user selects saved strategy and activates cbot
    When potential entry point is found
    Then user will see a buy order placed message displayed