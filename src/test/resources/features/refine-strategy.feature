Feature: refine strategy

  Background: A user has successfully registered/logged into their account
  and is at the home page

  Scenario: User refines strategy by adding all available choices
    Given strategy modal is open
    When user clicks on refine strategy widget
    And saves all these refinements to the strategy
      | stop-loss | 100   |
    Then user will see all saved refinements on load