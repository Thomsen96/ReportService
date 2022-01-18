package dtu.ReportService.Application;

import java.util.HashSet;

import com.google.gson.Gson;

import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;

public class ReportService {

	private ReportRepository reportRepository;

	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public HashSet<Payment> getCustomerReport(String customerId) {
		return reportRepository.getCustomerPayments(customerId);
	}

	public HashSet<PaymentMerchant> getMerchantReport(String merchantId) {
		return reportRepository.getMerchantPayments(merchantId);
	}

	public HashSet<Payment> getManagerReport() {
		return reportRepository.getManagerPayments();
	}
	
	public void put(Payment payment) {
		reportRepository.put(payment);
	}
}
