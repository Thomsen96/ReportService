package dtu.ReportService.Application;

import java.util.HashSet;
import java.util.List;

import dtu.ReportService.Domain.Entities.Token;
import dtu.ReportService.Domain.Interfaces.IReportRepository;

public class ReportService {

	private IReportRepository reportRepository;



	public ReportService(IReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public HashSet<Token> createTokens(Integer numOfTokens, String customerId) {
		if(numOfTokens > 0 && numOfTokens < 6 && reportRepository.get(customerId).size() < 2) {
			for( int i = 0; i < numOfTokens; i++) {
				reportRepository.create(customerId);
			}
		}
		return reportRepository.get(customerId);
	}

	public HashSet<Token> getTokens(String customerId) {
		return reportRepository.get(customerId);
	}

	public boolean deleteTokensForCustomer(String customerId) {
		return reportRepository.delete(customerId);
	}
	
	public Token getVerifiedToken(String tokenUuid) {
		return reportRepository.getVerfiedToken(tokenUuid);
	}
}
