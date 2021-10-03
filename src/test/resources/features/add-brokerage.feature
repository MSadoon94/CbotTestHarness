@add-brokerage
Feature: add brokerage

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User successfully adds a kraken brokerage account to their user account

    Given user selects add kraken brokerage
    When user submits brokerage information
    Then user will see kraken brokerage balance added to home page