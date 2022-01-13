package dtu.ReportService.Presentation.Resources;

import java.util.concurrent.CompletableFuture;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Entities.Token;
import messaging.Event;
import messaging.MessageQueue;

public class ReportMessageService {
	private MessageQueue messageQueue;
	private CompletableFuture<Boolean> customerVerified;
	private ReportService reportService;

	public ReportMessageService(MessageQueue messageQueue, ReportService reportService) {
		this.messageQueue = messageQueue;
		this.reportService = reportService;
		this.messageQueue.addHandler("TokenVerificationRequested", this::handleTokenVerificationRequest);
		this.messageQueue.addHandler("CustomerVerified", this::handleCustomerVerification);
	}


	// We send a verification request meant for AccountService with the customerId
	public Boolean verifyCustomer(String customerId) {
		customerVerified = new CompletableFuture<>();
		Event event = new Event("CustomerVerificationRequested", new Object[] { customerId });
		messageQueue.publish(event);
		return customerVerified.join();
	}

	// Handler for verification response from AccountService in the form of a boolean to see if the customer is registered.
	public void handleCustomerVerification(Event e) {
		var s = e.getArgument(0, Boolean.class);
		customerVerified.complete(s);
	}

	
	
	// Handler for verification request from Payments that needs to know if the token is valid and the cid for the token.
	public void handleTokenVerificationRequest(Event e) {
		var tokenUuid = e.getArgument(0, String.class);
		Token tokenObj = reportService.getVerifiedToken(tokenUuid);
		Event event = new Event("TokenVerificationResponse", new Object[] { tokenObj });
		messageQueue.publish(event);
	}
}
