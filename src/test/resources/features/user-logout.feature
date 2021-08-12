Feature: user logout

  Background: A user has successfully registered/logged into their account and is at their home page

    Given user has navigated to signup page
    When user enters these values
      | user name | password       |
      | TestUser  | TestPassword1- |
    And enters this value for password confirmation
      | password2      |
      | TestPassword1- |
    And clicks signup
    And clicks login
    And user will be redirected to user home page

  Scenario: User successfully logs out of their account

    When user selects log out
    Then user will see logged out alert