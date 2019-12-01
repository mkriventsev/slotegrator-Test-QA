Feature: Login functionality
  As a user,
  After correct form filling with correct credentials,
  I want to login with email and password

  @selenium
  Scenario: Logging in to Admin Dashboard with valid credentials

    Given I am at the login page
    When I logging in as user "admin1" with password "[9k<k8^z!+$$GkuP"
    And I click the Sign in button
    Then I should be at the dashboard home page
    And I should be logged as "admin1" user
