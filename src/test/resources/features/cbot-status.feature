@cbot-status
Feature: cbot status

  Background: A logged in user with a saved strategy is at the home page

  Scenario: User successfully activates cbot
    When user clicks on red start button
    Then user will see green start button

  @cbot-activated
  @no-login
  Scenario: Cbot stays activated even after user logs-out
    Given cbot is activated and user is logged out
    And user has navigated to login page
    When user enters these values
      | user name | password       |
      | TestUser  | TestPassword1- |
    And clicks login
    Then user will be redirected to user home page
    And user will see green start button