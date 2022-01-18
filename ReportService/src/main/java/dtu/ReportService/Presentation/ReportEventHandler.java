package dtu.ReportService.Presentation;

import dtu.ReportService.Application.ReportService;
import messaging.Event;
import messaging.MessageQueue;

public class ReportEventHandler {
	private MessageQueue messageQueue;
	private ReportService reportService;

	public ReportEventHandler(MessageQueue messageQueue, ReportService reportService) {
		this.messageQueue = messageQueue;
		this.reportService = reportService;
		this.messageQueue.addHandler("CustomerReportRequest", this::handleCustomerReportRequest);
		this.messageQueue.addHandler("MerchantReportRequest", this::handleMerchantReportRequest);
		this.messageQueue.addHandler("ManagerReportRequest", this::handleManagerReportRequest);
	}
	
	// Handler for verification request from Payments that needs to know if the token is valid and the cid for the token.
	public void handleCustomerReportRequest(Event e) {
		var sessionId = e.getArgument(0, String.class);
		var customerId = e.getArgument(1, String.class);
		var customerReport = reportService.getCustomerReport(customerId);
		Event event = new Event("CustomerReportResponse." + sessionId, new Object[] { customerReport });
		messageQueue.publish(event);
	}
	
	public void handleMerchantReportRequest(Event e) {
		var sessionId = e.getArgument(0, String.class);
		var merchantId = e.getArgument(1, String.class);
		var merchantReport = reportService.getMerchantReport(merchantId);
		Event event = new Event("MerchantReportResponse." + sessionId, new Object[] { merchantReport });
		messageQueue.publish(event);
	}
	
	public void handleManagerReportRequest(Event e) {
		var sessionId = e.getArgument(0, String.class);
		var managerReport = reportService.getManagerReport();
		Event event = new Event("ManagerReportResponse." + sessionId, new Object[] { managerReport });
		messageQueue.publish(event);
	}
}
