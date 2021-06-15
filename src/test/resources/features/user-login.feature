Feature: user login

  @login
  Scenario: A user enters correct credentials and is successfully redirected to user home page

    Given user has navigated to user start page

      | user name | password     |
      | testUser  | testPassword |

    When user submits these values for login

    Then user will be redirected to user home page