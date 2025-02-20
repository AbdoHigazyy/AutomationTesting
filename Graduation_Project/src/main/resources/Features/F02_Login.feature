@Smoke

Feature: F02_Login | users could use login functionality to use their accounts
  Scenario: user could login with valid email and password
  When user go to login page
  And 2 user login with valid "test@example.com" and "P@ssw0rd"
  And 3 user press on login button
  Then 4 user login to the system successfully

  Scenario: user could login with invalid email and password
  When user go to login page
  And user login with invalid "wrong@example.com" and "P@ssw0rd"
  And user press on login button
  Then user could not login to the system