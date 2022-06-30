Feature: trade metrics

  Background: User with a trade already created is on the home page

  Scenario: User attaches the trade metrics of an ongoing trade to the trades section
    Given user selects saved strategy and creates trade
    When trade is clicked on
    Then user will see candle graph attached to trade metrics section