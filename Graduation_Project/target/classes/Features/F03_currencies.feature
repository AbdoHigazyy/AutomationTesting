@Smoke
Feature: F03_currencies | users could use currency dropdown List functionality to change the currencies
  Scenario: user could change currencies with the dropDown list
    When user go to login page
    And 2 user login with valid "test@example.com" and "P@ssw0rd"
    And 3 user press on login button
    And  user select dropdown list on the top left of home page
    And user select Euro Currency
    Then Euro Symbol (€) is shown on the  products displayed in Home page