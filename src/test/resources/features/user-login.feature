Feature: user login

  Background: A user has successfully registered their account and is at their home page

    Given user has navigated to signup page
    When user enters these values
      | user name | password       |
      | TestUser  | TestPassword1- |
    And enters this value for password confirmation
      | password2      |
      | TestPassword1- |
    And clicks signup

  Scenario: A user enters correct credentials and is successfully redirected to user home page

    Given user has navigated to login page
    When user enters these values
      | user name | password       |
      | TestUser  | TestPassword1- |
    And clicks login
    Then user will be redirected to user home page

  Scenario: A user enters incorrect credentials and is not redirected to user home page

    Given user has navigated to login page
    When user enters these values
      | user name | password       |
      | Testuser  | TestPassword1- |
    And clicks login
    Then user will see failed login message