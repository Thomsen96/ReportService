package dtu.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;
import dtu.ReportService.Presentation.ReportEventHandler;
import dtu.ReportService.Presentation.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messaging.Event;

public class GetReportSteps {
	ReportService reportService = new ReportService(new ReportRepository());

	ArrayList<Payment> report;
	ArrayList<PaymentMerchant> merchantReport;
	String customer1, customer2, customer3, merchant1, merchant2, merchant3, token1, token2, token3, amount1, amount2, amount3;
	Payment payment1, payment2, payment3;
	
	Runner runner;
	
	@Given("a report exists")
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
		payment1 = new Payment(customer1, merchant1, token1, amount1);
		payment2 = new Payment(customer1, merchant2, token2, amount2);
		payment3 = new Payment(customer2, merchant1, token3, amount3);
		reportService.put(payment1);
		reportService.put(payment2);
		reportService.put(payment3);
	}

	@When("a manager requests a full report")
	public void aManagerRequestsAFullReport() {
		report = reportService.getManagerReport();
	}

	@When("a customer with id {string} requests a full report")
	public void aCustomerWithIdRequestsAFullReport(String customerId) {
		report = reportService.getCustomerReport(customerId);
	}
	
	@Then("the entire report is returned")
	public void theEntireReportIsReturned() {
		assertTrue(report.contains(payment1));
	}
	
	
	@When("a merchant with id {string} requests a full report")
	public void aMerchantWithIdRequestsAFullReport(String merchantId) {
		merchantReport = reportService.getMerchantReport(merchantId);
	}

	@Then("the entire merchant report is returned")
	public void theEntireMerchantReportIsReturned() {
		PaymentMerchant merchantPayment = new PaymentMerchant(payment1);
		assertTrue(merchantReport.contains(merchantPayment));
	}

}
