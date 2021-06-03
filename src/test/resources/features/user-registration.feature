Feature: user registration

  Scenario: A new user successfully creates a profile

    Given user has navigated to user registration page

      | user name | password     |
      | testUser  | testPassword |

    When user submits these values for user creation

    Then user will be created with same values

  Scenario: A user enters correct credentials and is successfully logged in to profile

    Given user has navigated to user registration page

      | user name | password     |
      | testUser  | testPassword |

    When user submits these values for login

    Then user will be logged in