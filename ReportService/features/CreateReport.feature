Feature: Create tokens feature

  Scenario: Manager requests a full report
  	Given a report exists
    When a manager requests a full report
    Then the entire report is returned
    
  Scenario: Merchant requests a full report
  	Given a report exists
    When a merchant with id "merchantId" requests a full report
    Then the entire report is returned
    
  Scenario: Customer requests a full report
  	Given a report exists
    When a customer with id "customerId" requests a full report
    Then the entire report is returned