package dtu.ReportService.Application;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;

import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;
import messaging.EventResponse;

public class ReportService {

	private ReportRepository reportRepository;

	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public ArrayList<Payment> getCustomerReport(String customerId) {
		return reportRepository.getCustomerPayments(customerId);
	}

	public ArrayList<PaymentMerchant> getMerchantReport(String merchantId) {
		return reportRepository.getMerchantPayments(merchantId);
	}

	public ArrayList<Payment> getManagerReport() {
		return reportRepository.getManagerPayments();
	}
	
	public void put(Payment payment) {
		reportRepository.put(payment);
	}
}
