Feature: Players table is available
  As a logged in user,
  After Selecting Users/Players tab in menu,
  I want to be at players page

  @selenium
  Scenario: Open players page with list table of players

    Given I logged in at dashboard home page
    When I click on the "Users" tab in menus
    Then Sub-menu "Users" is displayed
    When I click on the "Players" sub-tab
    Then I should be at the players page
    And The table with the players loaded
