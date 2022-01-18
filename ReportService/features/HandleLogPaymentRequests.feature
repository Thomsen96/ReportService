Feature: Handle log payment requests feature

  Scenario: PaymentService sends a payment that must be logged
    Given a payment has been completed
    When the payment is sent with sessionId "UniqueSessionId"
    Then payment is in the internal database