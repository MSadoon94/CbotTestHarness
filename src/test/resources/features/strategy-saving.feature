@strategy-saving
Feature: strategy saving

  Background: A user has successfully registered/logged into their account
  and is at the home page

  Scenario: User successfully saves strategy
    Given strategy modal is open
    When user clicks save strategy
    Then user will see save success message
