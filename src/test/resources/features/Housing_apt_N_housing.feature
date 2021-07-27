@sorting
Feature: Apartment and housing items sorting

  Background:
    Given user is at home page
    And selected language is "english"
    When user navigates to apts/housing page
    Then user should be on apt housing page

  @sortAscending
  Scenario: Validate items on apt and housing page are sorted in price ascending order
    When ascending price sort order is selected
    Then items should be displayed in price ascending order

#  @sortDescending
#  Scenario: Validate items on apt and housing page are sorted in price descending order
#    When descending price sort order is selected
#    Then items should be displayed in price descending order
#
#  @sortOnSearch
#  Scenario: Validate searched items on apt and housing page are sorted