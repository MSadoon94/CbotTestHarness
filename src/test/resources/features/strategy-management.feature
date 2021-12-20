Feature: strategy management

  Background: A user has successfully registered/logged into their account
  and is at the home page

  @strategy-saving
  Scenario: User successfully saves strategy
    Given strategy modal is open
    When user submits "MockStrategy" in strategy name input box
    Then user will see save success message

  Scenario: User successfully loads saved strategy
    Given strategy modal is open
    And user submits "MockStrategy" in strategy name input box
    When user clicks on target strategy in load strategy combo box
    Then strategy will be displayed in modal