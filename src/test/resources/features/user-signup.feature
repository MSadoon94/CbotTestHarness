Feature: user signup

  @signup
  Scenario: A new user successfully creates a user account

    Given user has navigated to signup page

    When user submits these signup values
      | user name | password     |
      | TestUser  | TestPassword |

    Then user will be created with same values