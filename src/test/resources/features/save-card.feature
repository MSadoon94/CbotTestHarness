Feature: save card

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User successfully adds kraken card to their user account

    Given user clicks new card button
    When user enters these card values
      | MockCard | MockAccount | MockPassword | MockKraken |
    And clicks save card button
    Then user will see card save success message

@fail-response
  Scenario Template: User fails to add card to their account due to input error

    Given user clicks new card button
    When user enters these card values
      | MockCard | MockAccount | MockPassword | MockKraken |
    And user enters this invalid "<input>" value
    And clicks save card button
    Then user will see save card fail message

    Scenarios:
      | input     |
      | Account   |
      | Password  |
      | Brokerage |