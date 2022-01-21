package dtu.services;

import static messaging.GLOBAL_STRINGS.REPORT_SERVICE.HANDLE.*;
import static messaging.GLOBAL_STRINGS.REPORT_SERVICE.PUBLISH.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Domain.ReportDTO;
import dtu.ReportService.Infrastructure.ReportRepository;
import dtu.ReportService.Presentation.ReportEventHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messaging.Event;
import messaging.EventResponse;
import messaging.implementations.MockMessageQueue;

public class ReportRequestsSteps {
	ReportService reportService = new ReportService(new ReportRepository());
	private static MockMessageQueue messageQueue = new MockMessageQueue();
	private ReportEventHandler reportEventHandler = new ReportEventHandler(messageQueue, reportService);

	String customer1, customer2, customer3, merchant1, merchant2, merchant3, token1, token2, token3, amount1, amount2, amount3;
	ArrayList<Payment> report;
	ArrayList<PaymentMerchant> merchantReport;
	String sessionId, userId;
	
	@Given("a custom report exists")
	public void aReportExists() {
		customer1 = "customerId1";
		customer2 = "customerId2";
		customer3 = "customerId3";
		merchant1 = "merchantId1";
		merchant2 = "merchantId2";
		merchant3 = "merchantId3";
		token1 = "uniqueTokenId1";
		token2 = "uniqueTokenId2";
		token3 = "uniqueTokenId3";
		amount1 = "300";
		amount2 = "200";
		amount3 = "50";
		Payment payment1 = new Payment(customer1, merchant1, token1, amount1);
		Payment payment2 = new Payment(customer1, merchant2, token2, amount2);
		Payment payment3 = new Payment(customer2, merchant1, token3, amount3);
		reportService.put(payment1);
		reportService.put(payment2);
		reportService.put(payment3);
	}
	
	@Given("a user with id {string}")
	public void aUserWithIdHasAReport(String userId) {
		this.userId = userId;
	}
	
	
	// Customer report request scenario
	@When("the customer requests his report with session id {string}")
	public void theCustomerRequestsHisReportWithSessionId(String sessionId) {
		this.sessionId = sessionId;
		EventResponse eventResponse = new EventResponse(sessionId, true, null, userId);
		Event reportRequestEvent = new Event(REPORT_CUSTOMER_REQUESTED, eventResponse);
		reportEventHandler.handleCustomerReportRequest(reportRequestEvent);
	}

	@Then("the customer report is put on the messagequeue")
	public void theCustomerReportIsPutOnTheMessagequeue() {
		var report = new ReportDTO.Customer(new ArrayList<>(reportService.getCustomerReport(userId).stream().map(Payment::toCustomerDTO).collect(Collectors.toList())));
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event expectedResponseEvent = new Event(REPORT_CUSTOMER_RESPONDED + sessionId, eventResponse );
		Event actualResponseEvent = messageQueue.getEvent(REPORT_CUSTOMER_RESPONDED + sessionId);
		assertEquals(eventResponse, actualResponseEvent.getArgument(0, EventResponse.class));
	}

	// Invalid request
	@Then("a customer error message {string} is put on the messagequeue")
	public void aCustomerErrorMessageIsPutOnTheMessagequeue(String expectedErrorMsg) {
		EventResponse eventResponse = new EventResponse(sessionId, false, expectedErrorMsg);
		Event expectedResponseEvent = new Event(REPORT_CUSTOMER_RESPONDED + sessionId, eventResponse);
		Event actualResponseEvent = messageQueue.getEvent(REPORT_CUSTOMER_RESPONDED + sessionId);
		assertEquals(expectedResponseEvent, actualResponseEvent);
	}	
	
	
	
	// Merchant report request scenario
	@When("the merchant requests his report with session id {string}")
	public void theMerchantRequestsHisReportWithSessionId(String sessionId) {
		this.sessionId = sessionId;
		EventResponse eventResponse = new EventResponse(sessionId, true, null, userId);
		Event reportRequestEvent = new Event(REPORT_MERCHANT_REQUESTED, new Object[] {eventResponse});
		reportEventHandler.handleMerchantReportRequest(reportRequestEvent);
	}
	@Then("the merchant report is put on the messagequeue")
	public void theMerchantReportIsPutOnTheMessagequeue() {
		var report = reportService.getMerchantReport(userId);
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event expectedResponseEvent = new Event(REPORT_MERCHANT_RESPONDED + sessionId, new Object[] { eventResponse });
		Event actualResponseEvent = messageQueue.getEvent(REPORT_MERCHANT_RESPONDED + sessionId);
		assertEquals(expectedResponseEvent, actualResponseEvent);
	}
	// Invalid request
	@Then("a merchant error message {string} is put on the messagequeue")
	public void aMerchantErrorMessageIsPutOnTheMessagequeue(String expectedErrorMsg) {
		EventResponse eventResponse = new EventResponse(sessionId, false, expectedErrorMsg);
		Event expectedResponseEvent = new Event(REPORT_MERCHANT_RESPONDED + sessionId, new Object[] { eventResponse });
		Event actualResponseEvent = messageQueue.getEvent(REPORT_MERCHANT_RESPONDED + sessionId);
		assertEquals(expectedResponseEvent, actualResponseEvent);
	}	
	
	

	// Manager report request scenario
	@When("the manager requests his report with session id {string}")
	public void theManagerRequestsHisReportWithSessionId(String sessionId) {
		this.sessionId = sessionId;
		EventResponse eventResponse = new EventResponse(sessionId, true, null);
		Event reportRequestEvent = new Event(REPORT_MANAGER_REQUESTED, new Object[] {eventResponse});
		reportEventHandler.handleManagerReportRequest(reportRequestEvent);
	}
	@Then("the manager report is put on the messagequeue")
	public void theManagerReportIsPutOnTheMessagequeue() {
		var report = reportService.getManagerReport();
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event expectedResponseEvent = new Event(REPORT_MANAGER_RESPONDED + sessionId, new Object[] { eventResponse });
		Event actualResponseEvent = messageQueue.getEvent(REPORT_MANAGER_RESPONDED + sessionId);
		assertEquals(expectedResponseEvent, actualResponseEvent);
	}
	
	@When("a handleReportStatusRequest with sessionId {string} is made")
	public void aHandleReportStatusRequestIsMade(String sessionId) {
		this.sessionId = sessionId; 
		EventResponse eventResponse = new EventResponse(sessionId, true, null, null);
		Event statusEvent = new Event(REST_STATUS_REQUESTED, eventResponse);
		reportEventHandler.handleReportStatusRequest(statusEvent);
	}

	@Then("an eventResponse with the message {string} is sent on the correct topic")
	public void anEventResponseWithTheMessageIsSent(String responseMessage) {
	    Event statusResponse = messageQueue.getEvent(REST_STATUS_RESPONDED + sessionId);
		assertNotNull(statusResponse);
	    EventResponse responseContent = statusResponse.getArgument(0, EventResponse.class);
	    assertEquals(responseMessage, responseContent.getArgument(0, String.class));
	}

}
