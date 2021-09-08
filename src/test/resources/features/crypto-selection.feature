@crypto-selection
Feature: crypto-selection

  Background: A user has successfully registered/logged into their account
  and the strategy creation pop-up is open on the home page

  Scenario: User enters valid cryptocurrency pair and is allowed to set it for the strategy
    Given strategy modal is open
    When user enters this symbol pair
      | Base | Quote |
      | BTC  | USD   |
    Then user will see checkmark next to entry

  Scenario: User enters invalid cryptocurrency pair and is not allowed to set it for the strategy
    Given strategy modal is open
    When user enters this symbol pair
      | Base | Quote |
      | BTC  | US    |
    Then user will see error message next to entry