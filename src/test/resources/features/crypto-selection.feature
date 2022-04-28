@crypto-selection
Feature: crypto-selection

  Background: A user has successfully registered/logged into their account
  and is at the home page

  Scenario: User enters valid cryptocurrency pair and is allowed to set it for the strategy
    Given strategy modal is open
    When user enters this symbol pair and exchange
      | Exchange | Base | Quote |
      | Kraken   | BTC  | USD   |
    Then user will see checkmark next to entry

  Scenario: User enters invalid cryptocurrency pair and is not allowed to set it for the strategy
    Given strategy modal is open
    When user enters this symbol pair and exchange
      | Exchange | Base | Quote |
      | Kraken   | BTC  | US   |
    Then user will see error message next to entry