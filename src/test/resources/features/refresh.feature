Feature: refresh

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: A user successfully refreshes a page

    When user refreshes page
    Then user will stay logged in and on the same page
