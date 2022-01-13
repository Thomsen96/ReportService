package dtu.services;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Domain.Entities.Token;
import dtu.ReportService.Domain.Repositories.LocalReportRepository;
import dtu.ReportService.Presentation.Resources.ReportMessageService;
import messaging.Event;
import messaging.MessageQueue;

public class VerifyTokenSteps {

  String customerId = null;
  Token token = null;

  private MessageQueue messageQueue = mock(MessageQueue.class);
  private ReportService reportService = new ReportService(new LocalReportRepository());
  private ReportMessageService service = new ReportMessageService(messageQueue, reportService);

  @Given("A customer with id {string}")
  public void aCustomerWithId(String customerId) {
    this.customerId = customerId;
    token = reportService.createTokens(1, customerId).stream().findFirst().get();
  }

  @When("a request to verify the token is received")
  public void aRequestToVerifyTheTokenIsReceived() {
    service.handleTokenVerificationRequest(new Event("TokenVerificationRequested",new Object[] {this.token.getUuid()}));
  }

  @Then("the token is verified")
  public void theTokenIsVerified() {
    Event event = new Event("TokenVerificationResponse", new Object[] { token });
	verify(messageQueue).publish(event);
  }

}