Feature: Handle report requests feature

  Scenario: Customer report request is handled and a report is returned
  	Given a custom report exists
    And a user with id "customerId1" has a payment in the report
    When the customer requests his report with session id "UniqueSessionId"
    Then the customer report is put on the messagequeue
    
  Scenario: Merchant report request is handled and a report is returned
  	Given a custom report exists
    And a user with id "merchantId1" has a payment in the report
    When the merchant requests his report with session id "UniqueSessionId"
    Then the merchant report is put on the messagequeue
    
  Scenario: Manager report request is handled and a report is returned
  	Given a custom report exists
    When the manager requests his report with session id "UniqueSessionId"
    Then the manager report is put on the messagequeue