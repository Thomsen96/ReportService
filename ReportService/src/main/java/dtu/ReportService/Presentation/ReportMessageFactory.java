package dtu.ReportService.Presentation;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Infrastructure.ReportRepository;
import messaging.implementations.RabbitMqQueue;

public class ReportMessageFactory {
  
  static ReportMessageService service = null;

	public ReportMessageService getService() {
		// The singleton pattern.
		// Ensure that there is at most
		// one instance of a PaymentService
		if (service != null) {
			return service;
		}
		
		// Hookup the classes to send and receive
		// messages via RabbitMq, i.e. RabbitMqSender and
		// RabbitMqListener. 
		// This should be done in the factory to avoid 
		// the PaymentService knowing about them. This
		// is called dependency injection.
		// At the end, we can use the PaymentService in tests
		// without sending actual messages to RabbitMq.
		var messageQueue = new RabbitMqQueue("rabbitMq");

		//TODO: Check how to add busniss logic here.
		
		service = new ReportMessageService(messageQueue, new ReportService(new ReportRepository()));
    //new StudentRegistrationServiceAdapter(service, mq);
		return service;
  }
}
