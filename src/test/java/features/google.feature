@TST-544
Feature: Test execution of Gherkin google example

	@TEST_TST-542
	Scenario: Google Search ChromeDriver
		Given I go to google
		    When I search "Chromedriver"
		    Then I should see "Chromedriver"
	@TEST_TST-543
	Scenario Outline: Searching a keyword
		Given I go to google
		      When I search "<food>"
		      Then I should see "<food>"
		
		      Examples:
		      | food    |
		      | pizza   |
		      | ham     |
