package dtu.ReportService.Application;

import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Infrastructure.ReportRepository;

public class ReportService {

	private ReportRepository reportRepository;

	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public String getCustomerReport(String customerId) {
		return reportRepository.getCustomerReport(customerId);
	}
	public String getMerchantReport(String customerId) {
		return reportRepository.getCustomerReport(customerId);
	}
	public String getManagerReport() {
		return reportRepository.getManagerReport();
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
