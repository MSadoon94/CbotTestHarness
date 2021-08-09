Feature: user signup

  Scenario: A user enters acceptable username/password, twice entered password matches, and user successfully signs up

    Given user has navigated to signup page
    When user enters these values
      | user name | password     | password2    |
      | TestUser  | TestPassword | TestPassword |
    And clicks signup
    Then user will be created with same values

  Scenario: A user enters an unacceptable username and cannot sign up

    Given user has navigated to signup page
    When user enters these values
      | user name | password     | password2    |
      | TestUser# | TestPassword | TestPassword |
    Then user will not be able to click the sign up button