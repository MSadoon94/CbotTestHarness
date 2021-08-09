Feature: add brokerage

  Background: A user has successfully registered/logged into their account and is at their home page

    Given user has navigated to signup page
    And user enters these values
      | user name | password     |
      | TestUser  | TestPassword |
    And clicks signup
    And clicks login
    And user will be redirected to user home page


  Scenario: User successfully adds a kraken brokerage account to their user account

    Given user selects add kraken brokerage
    When user submits this brokerage information
      | api key                                                  | private key                                                                              |
      | BMU9mcOU8FBQecpLHV2QrnXCTIH4595xBFaa7SZ2QMqqGGxzipKnU+Mc | soT5mu3aEv3Sf8XLEXQ0RwmlxoriV4L5lxslboyuQHSBqycpjPE4S7PTaHJ7A7tFQ8AH+BjuLkDYBO2JJEGT/w== |
    Then user will see kraken brokerage balance added to home page