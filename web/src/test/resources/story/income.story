Feature: record income

Background:
Given I am in the income page

Scenario: a success record income
When I input all necessary data
And click save button
Then show me a message
And display saved income on the page

Scenario: a fail record income
When I not input all necessary data
And click save button
Then show me a message