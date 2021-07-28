Feature: user login

  Background: A user has successfully registered their account and is at their home page
    Given user has navigated to signup page
    And user submits these signup values
      | user name | password     |
      | TestUser  | TestPassword |

  @login
  Scenario: A user enters correct credentials and is successfully redirected to user home page

    Given user has navigated to login page

    When user submits these login values
      | user name | password     |
      | TestUser  | TestPassword |

    Then user will be redirected to user home page
