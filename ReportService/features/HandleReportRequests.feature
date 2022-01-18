Feature: Handle report requests feature

  Scenario: Report request is handled and a report is returned
  	Given a report exists
    And a customer with id "customerId1" has a payment in the report
  # Send request
    When the customer requests his report
  # Verificer that it have been sent
    Then the customer report is put on the messagequeue