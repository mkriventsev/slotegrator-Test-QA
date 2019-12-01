Feature: Column ordering
  As a logged in user at players page,
  After clicking on column title,
  Data in column should be ordered according to clicked column in asc/desc order

  Background: I should be logged in
    Given I logged in at dashboard home page

  @selenium
  Scenario Outline: Check elements ordering in the player table

    Given I am at the players page
    And The table with the players loaded
    When I click on the column "<title>"
    Then Data in column "<title>" will be ordered "asc"
    When I click on the column "<title>"
    Then Data in column "<title>" will be ordered "desc"
    Examples:
      | title |
      | Username |
      | External ID |
      | Name |
      | Last name |
      | E-mail |
      | Registration date |
