package dtu.ReportService.Presentation;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import messaging.Event;
import messaging.EventResponse;
import messaging.MessageQueue;

public class ReportEventHandler {
	private MessageQueue messageQueue;
	private ReportService reportService;

	public ReportEventHandler(MessageQueue messageQueue, ReportService reportService) {
		this.messageQueue = messageQueue;
		this.reportService = reportService;
		this.messageQueue.addHandler("LogPaymentRequest", this::handleLogPaymentRequest);
		this.messageQueue.addHandler("CustomerReportRequest", this::handleCustomerReportRequest);
		this.messageQueue.addHandler("MerchantReportRequest", this::handleMerchantReportRequest);
		this.messageQueue.addHandler("ManagerReportRequest", this::handleManagerReportRequest);
		this.messageQueue.addHandler("ReportStatusRequest", this::handleReportStatusRequest);
	}
	
	public void handleLogPaymentRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var payment = eventArguments.getArgument(0, Payment.class);
		reportService.put(payment);
	}
	
	public void handleCustomerReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		var customerId = eventArguments.getArgument(0, String.class);
		
		var report = reportService.getCustomerReport(customerId);
		EventResponse eventResponse;
		if(report != null) {
			eventResponse = new EventResponse(sessionId, true, null, report);
		}
		else {
			String errorMsg = "There are no logged payments for user: " + customerId;
			System.out.println(errorMsg);
			eventResponse = new EventResponse(sessionId, false, errorMsg);
		}
		Event outgoingEvent = new Event("CustomerReportResponse." + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}
	
	public void handleMerchantReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		var merchantId = eventArguments.getArgument(0, String.class);
		
		var report = reportService.getMerchantReport(merchantId);
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event outgoingEvent = new Event("MerchantReportResponse." + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}
	
	public void handleManagerReportRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		
		var report = reportService.getManagerReport();
		EventResponse eventResponse = new EventResponse(sessionId, true, null, report);
		Event outgoingEvent = new Event("ManagerReportResponse." + sessionId, new Object[] { eventResponse });
		messageQueue.publish(outgoingEvent);
	}
	
	public void handleReportStatusRequest(Event incommingEvent) {
		EventResponse eventArguments = incommingEvent.getArgument(0, EventResponse.class);
		var sessionId = eventArguments.getSessionId();
		
		EventResponse eventResponse = new EventResponse(sessionId, true, null, "Report service ready");
		Event outgoingEvent = new Event("ReportStatusResponse." + sessionId, new Object[] {eventResponse});
		messageQueue.publish(outgoingEvent);
	}
}
