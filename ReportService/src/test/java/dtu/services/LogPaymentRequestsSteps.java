package dtu.services;

import static messaging.GLOBAL_STRINGS.REPORT_SERVICE.HANDLE.LOG_PAYMENT_REQUESTED;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;
import dtu.ReportService.Presentation.ReportEventHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messaging.Event;
import messaging.EventResponse;
import messaging.implementations.MockMessageQueue;

public class LogPaymentRequestsSteps {
	ReportService reportService = new ReportService(new ReportRepository());
	private static MockMessageQueue messageQueue = new MockMessageQueue();
	private ReportEventHandler reportEventHandler = new ReportEventHandler(messageQueue, reportService);
	
	Payment payment;
	String sessionId, customerId, merchantId;
	
	@Given("a payment has been completed")
	public void aPaymentHasBeenCompleted() {
		customerId = "customerId1";
		merchantId = "merchantId1";
		String token1 = "uniqueTokenId1";
		String amount1 = "300";
		payment = new Payment(customerId, merchantId, token1, amount1);
	}
	
	// Customer report request scenario
	@When("the payment is sent with sessionId {string}")
	public void thePaymentIsSentWithSessionId(String sessionId) {
		this.sessionId = sessionId;
		EventResponse eventResponse = new EventResponse(sessionId, true, null, payment);
		Event reportRequestEvent = new Event(LOG_PAYMENT_REQUESTED, new Object[] {eventResponse});
		reportEventHandler.handleLogPaymentRequest(reportRequestEvent);
	}
	
	@Then("payment is in the internal database")
	public void paymentIsInTheInternalDatabase() {
		PaymentMerchant merchantPayment = new PaymentMerchant(payment);
		assertTrue(reportService.getMerchantReport(merchantId).contains(merchantPayment));
		assertTrue(reportService.getCustomerReport(customerId).contains(payment));
		assertTrue(reportService.getManagerReport().contains(payment));
	}
}
