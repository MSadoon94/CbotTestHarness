Feature: user login

  Background: A user has successfully registered their account and is at their home page

    Given user has navigated to signup page
    And user enters these values
      | user name | password     |
      | TestUser  | TestPassword |
    And clicks signup

  Scenario: A user enters correct credentials and is successfully redirected to user home page

    Given user has navigated to login page
    When user enters these values
      | user name | password     |
      | TestUser  | TestPassword |
    And clicks login
    Then user will be redirected to user home page

  Scenario: A user enters incorrect credentials and is not redirected to user home page

    Given user has navigated to login page
    When user enters these values
      | user name | password     |
      | Testuser  | Testpassword |
    And clicks login
    Then user will see failed login message