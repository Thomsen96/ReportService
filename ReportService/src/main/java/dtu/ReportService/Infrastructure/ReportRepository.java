package dtu.ReportService.Infrastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dtu.ReportService.Domain.Payment;
import dtu.ReportService.Domain.Token;

public class ReportRepository {
	Gson gson = new Gson(); 
	// 		cid		set of payments for customer
	HashMap<String, HashSet<Payment>> customerReport = new HashMap<>();
	// 		mid		set of payments for merchant
	HashMap<String, HashSet<Payment>> merchantReport = new HashMap<>();
	// 		tid		set all payments
	HashMap<String, Payment> managerReport = new HashMap<>();
	
	public String getCustomerReport(String customerId) {
		String reportAsJsonString = gson.toJson(customerReport.get(customerId));
		return reportAsJsonString;
	}

	public String getMerchantReport(String merchantId) {
		String reportAsJsonString = gson.toJson(merchantReport.get(merchantId));
		return reportAsJsonString;
	}

	public String getManagerReport() {
		String reportAsJsonString = gson.toJson(managerReport);
		return reportAsJsonString;
	}
	
	public void put(Payment payment) {
		managerReport.put(payment.getToken(), payment);
		
		if (customerReport.containsKey(payment.getCustomerId())) {
			customerReport.get(payment.getCustomerId()).add(payment);
		}
		else {
			var paymentSet = new HashSet<Payment>();
			paymentSet.add(payment);
			customerReport.put(payment.getCustomerId(), paymentSet);
		}
		
		// TODO: Remember merchantReports shouldn't contain customer info
		if (merchantReport.containsKey(payment.getMerchantId())) {
			merchantReport.get(payment.getMerchantId()).add(payment);
		}
		else {
			var paymentSet = new HashSet<Payment>();
			paymentSet.add(payment);
			merchantReport.put(payment.getMerchantId(), paymentSet);
		}
		
	}
	
	
	
	
	
//	@Override
//	public HashSet<Token> get(String customerId) {
//		if(!customerHashMap.containsKey(customerId)) {
//			customerHashMap.put(customerId, new HashSet<Token>());
//		}
//		return customerHashMap.get(customerId);
//	}
//
//	@Override
//	public Token create(String customerId) {
//		Token token = new Token(customerId);
//		tokenHashMap.put(token.getUuid(), token);
//		if (customerHashMap.containsKey(customerId)) {
//			customerHashMap.get(customerId).add(token);
//		}
//		else {
//			var tokenSet = new HashSet<Token>();
//			tokenSet.add(token);
//			customerHashMap.put(customerId, tokenSet);
//		}
//		return token;
//	}
//
//	@Override
//	public boolean delete(String customerId) {
//		var tokensToRemove = customerHashMap.remove(customerId);
//		for (Token token : tokensToRemove) {
//			tokenHashMap.remove(token.getUuid());
//		}
//		return true;
//	}
//
//	@Override
//	public Token getVerfiedToken(String tokenUuid) {
//		try {
//			var token = tokenHashMap.remove(tokenUuid);
//			customerHashMap.get(token.getCustomerId()).remove(token);
//			return token;
//		} catch (Exception e) {
//			return new Token(false);
//		}
//	}
//	@Override
//	public HashSet<Token> getAll() {
//		return null;
//	}

}
