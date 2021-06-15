Feature: user signup

  Scenario: A new user successfully creates a user account

    Given user has navigated to user start page

      | user name | password     |
      | testUser  | testPassword |

    When user submits these values for user creation

    Then user will be created with same values