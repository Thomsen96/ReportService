package dtu.services;

import java.util.HashSet;
import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportRequestsSteps {
	ReportService reportService = new ReportService(new ReportRepository());
	HashSet<Payment> report;
	HashSet<PaymentMerchant> merchantReport;
	String customerId, customer1, customer2, customer3, merchant1, merchant2, merchant3, token1, token2, token3, amount1, amount2, amount3;

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
		Payment payment1 = new Payment(customer1, merchant1, token1, amount1);
		Payment payment2 = new Payment(customer1, merchant2, token2, amount2);
		Payment payment3 = new Payment(customer2, merchant1, token3, amount3);
		reportService.put(payment1);
		reportService.put(payment2);
		reportService.put(payment3);
	}
	
	@Given("a customer with id {string} has a payment in the report")
	public void aCustomerWithIdHasAReport(String customerId) {
		this.customerId = customerId;
		
	}

	@When("the customer requests his report")
	public void theCustomerRequestsHisReport() {
		report = reportService.getManagerReport();
	}
	
	@Then("the customer report is put on the messagequeue")
	public void theCustomerReportIsPutOnTheMessagequeue() {
	}

}
