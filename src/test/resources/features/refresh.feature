Feature: refresh

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: A user successfully refreshes a page

    When user refreshes page
    Then user will stay logged in and on the same page

  @silent-refresh
  Scenario: A user successfully silently refreshes a page

    Given user clicks on load card combo box
    When session silently refreshes
    Then user will stay logged in and on the same page

  @refresh-fail
  Scenario: A user fails to reload a page after refreshing due to session ending

    When user refreshes page
    Then a session end alert will be shown
    And user will be sent to start page

  @refresh-fail
  Scenario: A user fails to silently refresh a page and is sent back to start page

    Given user clicks on load card combo box
    When a session end alert will be shown
    Then user will be sent to start page