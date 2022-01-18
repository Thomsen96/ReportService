package dtu.ReportService.Presentation;

import java.util.concurrent.CompletableFuture;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Token;
import messaging.Event;
import messaging.MessageQueue;

public class ReportMessageService {
//	private CompletableFuture<Boolean> customerVerified;
	private MessageQueue messageQueue;
	private ReportService reportService;

	public ReportMessageService(MessageQueue messageQueue, ReportService reportService) {
		this.messageQueue = messageQueue;
		this.reportService = reportService;
		this.messageQueue.addHandler("CustomerReportRequest", this::handleCustomerReportRequest);
		this.messageQueue.addHandler("MerchantReportRequest", this::handleMerchantReportRequest);
		this.messageQueue.addHandler("ManagerReportRequest", this::handleManagerReportRequest);
//		this.messageQueue.addHandler("CustomerVerified", this::handleCustomerVerification);
	}
	// Handler for verification request from Payments that needs to know if the token is valid and the cid for the token.
	public void handleCustomerReportRequest(Event e) {
		var customerId = e.getArgument(0, String.class);
		String customerReport = reportService.getCustomerReport(customerId);
		Event event = new Event("CustomerReportRequest", new Object[] { customerReport });
		messageQueue.publish(event);
	}
	public void handleMerchantReportRequest(Event e) {
		var merchantId = e.getArgument(0, String.class);
		String merchantReport = reportService.getMerchantReport(merchantId);
		Event event = new Event("MerchantReportRequest", new Object[] { merchantReport });
		messageQueue.publish(event);
	}
	public void handleManagerReportRequest(Event e) {
		String managerReport = reportService.getManagerReport();
		Event event = new Event("ManagerReportRequest", new Object[] { managerReport });
		messageQueue.publish(event);
	}
	
	


//	// We send a verification request meant for AccountService with the customerId
//	public Boolean verifyCustomer(String customerId) {
//		customerVerified = new CompletableFuture<>();
//		Event event = new Event("CustomerVerificationRequested", new Object[] { customerId });
//		messageQueue.publish(event);
//		return customerVerified.join();
//	}
//
//	// Handler for verification response from AccountService in the form of a boolean to see if the customer is registered.
//	public void handleCustomerVerification(Event e) {
//		var s = e.getArgument(0, Boolean.class);
//		customerVerified.complete(s);
//	}
}
