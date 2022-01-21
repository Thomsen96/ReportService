package dtu.ReportService.Presentation;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import messaging.Event;
import messaging.EventResponse;
import messaging.MessageQueue;

import static messaging.GLOBAL_STRINGS.REPORT_SERVICE.HANDLE.*;
import static messaging.GLOBAL_STRINGS.REPORT_SERVICE.PUBLISH.*;

import java.util.ArrayList;

public class ReportEventHandler {
	private MessageQueue messageQueue;
	private ReportService reportService;

	public ReportEventHandler(MessageQueue messageQueue, ReportService reportService) {
		this.messageQueue = messageQueue;
		this.reportService = reportService;
		this.messageQueue.addHandler(LOG_PAYMENT_REQUESTED, this::handleLogPaymentRequest);
		this.messageQueue.addHandler(REPORT_CUSTOMER_REQUESTED, this::handleCustomerReportRequest);
		this.messageQueue.addHandler(REPORT_MERCHANT_REQUESTED, this::handleMerchantReportRequest);
		this.messageQueue.addHandler(REPORT_MANAGER_REQUESTED, this::handleManagerReportRequest);
		this.messageQueue.addHandler(REPORT_STATUS_REQUESTED, this::handleReportStatusRequest);
	}

	public void handleLogPaymentRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		Payment payment = eventArguments.getArgument(0, Payment.class);
		System.out.println("Received payment: " + payment);
		reportService.put(payment);
		System.out.println("Payment has been logged");
	}
	
	public void handleCustomerReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		String sessionId = eventArguments.getSessionId();
		String customerId = eventArguments.getArgument(0, String.class);
		System.out.println("Handling customer report request for id: " + customerId);
		
		ArrayList<Payment> report = reportService.getCustomerReport(customerId);
		System.out.println("Report: " + report);
		boolean validRequest = report != null;
		EventResponse eventResponse;
		if(validRequest) {
			System.out.println("Valid request");
			eventResponse = new EventResponse(sessionId, validRequest, null, report);
		}
		else {
			String errorMsg = "There are no logged payments for user: " + customerId;
			System.out.println("Invalid request. " + errorMsg);
			eventResponse = new EventResponse(sessionId, validRequest, errorMsg);
		}
		Event outgoingEvent = new Event(REPORT_CUSTOMER_RESPONDED + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}

	
	public void handleMerchantReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		var merchantId = eventArguments.getArgument(0, String.class);
		
		var report = reportService.getMerchantReport(merchantId);
		boolean validRequest = report != null;
		EventResponse eventResponse;
		if(validRequest) {
			eventResponse = new EventResponse(sessionId, validRequest, null, report);
		}
		else {
			String errorMsg = "There are no logged payments for user: " + merchantId;
			System.out.println(errorMsg);
			eventResponse = new EventResponse(sessionId, validRequest, errorMsg);
		}
		Event outgoingEvent = new Event(REPORT_MERCHANT_RESPONDED + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}
	
	public void handleManagerReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		
		var report = reportService.getManagerReport();
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event outgoingEvent = new Event(REPORT_MANAGER_RESPONDED + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}
	
	public void handleReportStatusRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		
		EventResponse eventResponse = new EventResponse(sessionId, true, null, "Report service ready");
		Event outgoingEvent = new Event(REPORT_STATUS_RESPONDED + sessionId, new Object[] {eventResponse});
		messageQueue.publish(outgoingEvent);
	}
}
