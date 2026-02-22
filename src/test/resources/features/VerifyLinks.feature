@Test

Feature: Verify Links in Screener website

  Scenario: Verify pipeline test execution
    Given the user navigates to website
    Then the user extracts the links from the page
    And validates the link successfully