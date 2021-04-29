Feature: profile creation

  Scenario: A new user creates a profile

    Given user has navigated to profile creation page

    When user submits these values

      | profile name | password     |
      | testProfile  | testPassword |

    Then profile will be created with same values