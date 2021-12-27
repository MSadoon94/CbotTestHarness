Feature: refine strategy

  Background: A user has successfully registered/logged into their account
  and is at the home page

  Scenario: User successfully adds valid stop loss
    Given strategy modal is open
    When user enters "100" in stop loss entry box
    Then user will see "✔" next to "stop-loss" entry box

  Scenario: User fails to add invalid stop loss
    Given strategy modal is open
    When user enters "invalid" in stop loss entry box
    Then user will see "X" next to "stop-loss" entry box