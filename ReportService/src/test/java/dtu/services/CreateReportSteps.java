package dtu.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Entities.Token;
import dtu.ReportService.Domain.Repositories.LocalReportRepository;
//import dtu.TokenService.Presentation.Resources.ReportMessageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import messaging.MessageQueue;

public class CreateReportSteps {
	String customerId = null;
	String merchantId = null;
	//private MessageQueue messageQueue = mock(MessageQueue.class);
	ReportService reportService = new ReportService(new LocalReportRepository());
	//private ReportMessageService service = new ReportMessageService(messageQueue, reportService);
	HashSet<Token> tokens = new HashSet<>();

	@Given("a customer with id {string}")
	public void aCustomerWithId(String customerId) {
		this.customerId = customerId;
	}

	@Given("the customer already has {int} tokens")
	public void theCustomerAlreadyHasTokens(Integer numOfTokens) {
		tokens = reportService.createTokens(numOfTokens, customerId);
	}

	@When("the customer requests {int} tokens")
	public void theCustomerRequestsTokens(Integer numOfTokens) {
		tokens = reportService.createTokens(numOfTokens, customerId);
	}

	@Then("the customer has {int} tokens")
	public void theCustomerHasTokens(Integer expectedNumOfTokens) {
		assertEquals(expectedNumOfTokens, tokens.size());
	}
}
