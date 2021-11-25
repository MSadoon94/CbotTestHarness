@sign-up
Feature: user signup

  Scenario: A user successfully signs up after entering acceptable username/password and matching password entries

    Given user has navigated to signup page
    When user enters these values
      | user name | password       |
      | TestUser  | TestPassword1- |
    And enters this value for password confirmation
      | password2      |
      | TestPassword1- |
    And clicks signup
    Then user will be created with same values

  Scenario: A user enters an unacceptable username and cannot sign up

    Given user has navigated to signup page
    When user enters these values
      | user name | password       |
      | TestUser# | TestPassword1- |
    And enters this value for password confirmation
      | password2      |
      | TestPassword1- |
    Then user will not be able to click the sign up button

  Scenario: A user enters an unacceptable password and cannot sign up

    Given user has navigated to signup page
    When user enters these values
      | user name | password      |
      | TestUser  | TestPassword1 |
    And enters this value for password confirmation
      | password2     |
      | TestPassword1 |
    Then user will not be able to click the sign up button

  Scenario: A user cannot sign up because the first entry of their password does not match their second entry

    Given user has navigated to signup page
    When user enters these values
      | user name | password      |
      | TestUser  | TestPassword1- |
    And enters this value for password confirmation
      | password2      |
      | TestPassword1 |
    Then user will not be able to click the sign up button