package dtu.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.Token;
import dtu.ReportService.Infrastructure.ReportRepository;
//import dtu.TokenService.Presentation.Resources.ReportMessageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import messaging.MessageQueue;
import io.restassured.internal.path.json.JSONAssertion;

public class CreateReportSteps {
	ReportService reportService = new ReportService(new ReportRepository());
	String report, customer1, customer2, customer3, merchant1, merchant2, merchant3, token1, token2, token3, amount1, amount2, amount3;

	@Given("a report exists")
	public void aReportExists() {
		customer1 = "customerId1";
		customer2 = "customerId";
		customer3 = "customerId";
		merchant1 = "merchantId1";
		merchant2 = "merchantId";
		merchant3 = "merchantId";
		token1 = "uniqueTokenId1";
		token2 = "uniqueTokenId2";
		token3 = "uniqueTokenId3";
		amount1 = "300";
		amount2 = "200";
		amount3 = "50";
		Payment payment1 = new Payment(customer1, merchant1, token1, amount1);
		Payment payment2 = new Payment(customer1, merchant1, token2, amount2);
		Payment payment3 = new Payment(customer1, merchant1, token3, amount3);
		reportService.put(payment1);
		reportService.put(payment2);
		reportService.put(payment3);
	}

	@When("a manager requests a full report")
	public void aManagerRequestsAFullReport() {
		report = reportService.getManagerReport();
	}

	@When("a merchant with id {string} requests a full report")
	public void aMerchantWithIdRequestsAFullReport(String merchantId) {
		report = reportService.getMerchantReport(merchantId);
	}

	@When("a customer with id {string} requests a full report")
	public void aCustomerWithIdRequestsAFullReport(String customerId) {
		report = reportService.getCustomerReport(customerId);
	}
	
	@Then("the entire report is returned")
	public void theEntireReportIsReturned() {
		JsonObject expectedReport = new JsonObject();
		JsonElement paymentJson;
//		paymentJson.
//		expectedReport.add(token1, expectedReport);
	}
	
//	@Given("a customer with id {string}")
//	public void aCustomerWithId(String customerId) {
//		this.customerId = customerId;
//	}
//
//	@Given("the customer already has {int} tokens")
//	public void theCustomerAlreadyHasTokens(Integer numOfTokens) {
//		tokens = reportService.createTokens(numOfTokens, customerId);
//	}
//
//	@When("the customer requests {int} tokens")
//	public void theCustomerRequestsTokens(Integer numOfTokens) {
//		tokens = reportService.createTokens(numOfTokens, customerId);
//	}
//
//	@Then("the customer has {int} tokens")
//	public void theCustomerHasTokens(Integer expectedNumOfTokens) {
//		assertEquals(expectedNumOfTokens, tokens.size());
//	}

}
