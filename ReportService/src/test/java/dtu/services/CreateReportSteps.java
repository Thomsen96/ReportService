package dtu.services;

import java.util.HashSet;
import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateReportSteps {
	ReportService reportService = new ReportService(new ReportRepository());
	HashSet<Payment> report;
	HashSet<PaymentMerchant> merchantReport;
	String customer1, customer2, customer3, merchant1, merchant2, merchant3, token1, token2, token3, amount1, amount2, amount3;

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
		merchantReport = reportService.getMerchantReport(merchantId);
	}

	@When("a customer with id {string} requests a full report")
	public void aCustomerWithIdRequestsAFullReport(String customerId) {
		report = reportService.getCustomerReport(customerId);
	}
	
	@Then("the entire report is returned")
	public void theEntireReportIsReturned() {
	}

}
