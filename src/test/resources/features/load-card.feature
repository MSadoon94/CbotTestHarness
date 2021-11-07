@load-card
Feature: load card

  Background: A user has successfully registered/logged into their account
  and is on the home page

  Scenario: User inputs valid password and successfully loads a card onto home page

    Given user clicks on target card in load card combo box
    When user submits valid password for password validation
    Then target card will be displayed on home page

  Scenario: User inputs invalid password and fails to load a card onto home page

    Given user clicks on target card in load card combo box
    When user submits invalid password for password validation
    Then card load error will be displayed