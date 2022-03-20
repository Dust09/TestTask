@checkSorting
Feature: Check sorted in table
  Scenario: login and select players table and make sorted
    Given Go to the site "http://test-app.d6.dev.devcaz.com/admin/login"
    When user login "admin1" and password "[9k<k8^z!+$$GkuP" and click login
    Then click on players table
    And click on registration data column
    And check sorting is work


