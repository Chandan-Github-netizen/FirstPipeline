

Feature: Verify Links in Screener website

  @Smoke
  Scenario: Verify pipeline test execution
    Given the user navigates to website
    Then the user extracts the links from the page
    And validates the link successfully


  @Regression
  Scenario: Checking scenario for regression
    Given print regression is running