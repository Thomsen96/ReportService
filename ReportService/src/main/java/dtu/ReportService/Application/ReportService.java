package dtu.ReportService.Application;

import java.util.HashSet;

import com.google.gson.Gson;

import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Infrastructure.ReportRepository;

public class ReportService {

	private ReportRepository reportRepository;
	Gson gson = new Gson(); 

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

	//	public HashSet<Token> createTokens(Integer numOfTokens, String customerId) {
	//		if(numOfTokens > 0 && numOfTokens < 6 && reportRepository.get(customerId).size() < 2) {
	//			for( int i = 0; i < numOfTokens; i++) {
	//				reportRepository.create(customerId);
	//			}
	//		}
	//		return reportRepository.get(customerId);
	//	}
	//
	//	public HashSet<Token> getTokens(String customerId) {
	//		return reportRepository.get(customerId);
	//	}
	//
	//	public boolean deleteTokensForCustomer(String customerId) {
	//		return reportRepository.delete(customerId);
	//	}
	//	
	//	public Token getVerifiedToken(String tokenUuid) {
	//		return reportRepository.getVerfiedToken(tokenUuid);
	//	}
}
