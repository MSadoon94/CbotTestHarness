Feature: exchange management

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User successfully adds exchange to their user account

    Given user clicks on side bar
    When user enters these exchange values
      | Kraken | MockAccount | MockPassword |
    And clicks add credential button
    Then user will see exchange card in side bar
