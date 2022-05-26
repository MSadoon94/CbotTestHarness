Feature: refine strategy

  Background: A user has successfully registered/logged into their account
  and is at the home page

  Scenario: User refines strategy by adding all available choices
    Given strategy modal is open
    And user enters this symbol pair and exchange
      | Exchange | Base | Quote |
      | Kraken   | BTC  | USD   |
    When user saves all these refinements to the strategy
      | stop-loss        | 100  |
      | max-position     | 200  |
      | target-profit    | 5    |
      | moving-stop-loss | 10   |
      | max-loss         | 1000 |
      | entry            | 10   |
    Then user will see all saved refinements on load