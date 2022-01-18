package dtu.ReportService.Presentation;
import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Infrastructure.ReportRepository;
import messaging.MessageQueue;

public class Runner {
  public static void main(String[] args) {
    MessageQueue messageQueue = new MessageQueueFactory().getMessageQueue();
    ReportService reportService = new ReportService(new ReportRepository());
    ReportEventHandler handler = new ReportEventHandler(messageQueue, reportService);
    System.out.println(handler.toString() + " Token service started");
  }
}