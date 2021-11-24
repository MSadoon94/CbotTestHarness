Feature: save card

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User successfully adds card to their user account

    Given user clicks new card button
    When user submits valid card information
    Then user will see card save success message

  Scenario Template: User fails to add card to their account due to input error

    Given user clicks new card button
    When user submits this invalid card "<input>"
    Then user will see save card fail message

    Scenarios:
      | input     |
      | Account   |
      | Password  |
      | Brokerage |