Feature: user signup

  @signup
  Scenario: A new user successfully creates a user account

    Given user has navigated to signup page
    When user enters these values
      | user name | password     |
      | TestUser  | TestPassword |
    And clicks signup
    Then user will be created with same values