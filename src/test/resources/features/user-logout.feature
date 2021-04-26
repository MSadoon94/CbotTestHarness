Feature: : user logout

  Background: A user has successfully registered/logged into their account and is at their home page
    Given user has navigated to signup page
    And user submits these signup values
      | user name | password     |
      | TestUser  | TestPassword |
    And user submits these login values
      | user name | password     |
      | TestUser  | TestPassword |
    And user will be redirected to user home page

  Scenario: User successfully logs out of their account

    When user selects log out

    Then user will see logged out alert