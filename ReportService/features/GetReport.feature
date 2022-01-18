Feature: Get report feature

  Scenario: Manager requests a full report
  	Given a report exists
    When a manager requests a full report
    Then the entire report is returned
    
  Scenario: Merchant requests a full report
  	Given a report exists
    When a merchant with id "merchantId1" requests a full report
    Then the entire merchant report is returned

  Scenario: Customer requests a full report
  	Given a report exists
    When a customer with id "customerId1" requests a full report
    Then the entire report is returned