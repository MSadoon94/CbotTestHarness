@cbot-activation
Feature: cbot activation

  Background: A logged in user with a saved strategy is at the home page

  Scenario: User successfully activates cbot
    Given user selects strategy for cbot to use
    When user clicks on red start button
    Then user will see start button change to green