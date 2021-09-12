@logout
Feature: user logout

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User successfully logs out of their account

    When user selects log out
    Then user will see logged out alert