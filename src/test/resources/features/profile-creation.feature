Feature: profile creation

  Scenario: A new user creates a profile

    Given user has navigated to profile creation page

    When user submits these values

      | username | password | profile name |
      | john     | doe      | test profile |

    Then profile will be created with same values