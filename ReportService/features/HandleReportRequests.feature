Feature: Handle report requests feature

  Scenario: Customer report request is handled and a report is returned
  	Given a custom report exists
    And a user with id "customerId1"
    When the customer requests his report with session id "SessionIdForValidRequest"
    Then the customer report is put on the messagequeue
    
  Scenario: Customer report invalid request is handled and an error message is returned
  	Given a custom report exists
    And a user with id "invalidCustomerId"
    When the customer requests his report with session id "SessionIdForInvalidRequest"
    Then a customer error message "There are no logged payments for user: invalidCustomerId" is put on the messagequeue
    
  Scenario: Merchant report request is handled and a report is returned
  	Given a custom report exists
    And a user with id "merchantId1"
    When the merchant requests his report with session id "UniqueSessionId"
    Then the merchant report is put on the messagequeue
    
  Scenario: Merchant report invalid request is handled and an error message is returned
  	Given a custom report exists
    And a user with id "invalidMerchantId"
    When the merchant requests his report with session id "SessionIdForInvalidRequest"
    Then a merchant error message "There are no logged payments for user: invalidMerchantId" is put on the messagequeue
    
    
  Scenario: Manager report request is handled and a report is returned
  	Given a custom report exists
    When the manager requests his report with session id "UniqueSessionId"
    Then the manager report is put on the messagequeue