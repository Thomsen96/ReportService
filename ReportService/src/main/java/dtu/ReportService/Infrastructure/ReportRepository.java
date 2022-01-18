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
import dtu.ReportService.Domain.PaymentMerchant;
import dtu.ReportService.Domain.Token;

public class ReportRepository {
	Gson gson = new Gson(); 
	// 		cId		set of payments for customer
	HashMap<String, HashSet<Payment>> customerReport = new HashMap<>();
	// 		mId		set of payments for merchant
	HashMap<String, HashSet<PaymentMerchant>> merchantReport = new HashMap<>();
	// 		tId		set all payments
	HashSet<Payment> managerReport = new HashSet<>();

	
	public HashSet<Payment> getCustomerPayments(String customerId) {
		if(customerReport.containsKey(customerId)) {
			return customerReport.get(customerId);
		}
		return null;
	}
	
	public HashSet<PaymentMerchant> getMerchantPayments(String merchantId) {
		if(merchantReport.containsKey(merchantId)) {
			return merchantReport.get(merchantId);
		}
		return null;
	}
	
	public HashSet<Payment> getManagerPayments() {
		return managerReport;
	}
	
	public void put(Payment payment) {
		managerReport.add(payment);
		
		if (customerReport.containsKey(payment.getCustomerId())) {
			customerReport.get(payment.getCustomerId()).add(payment);
		}
		else {
			var paymentSet = new HashSet<Payment>();
			paymentSet.add(payment);
			customerReport.put(payment.getCustomerId(), paymentSet);
		}
		
		PaymentMerchant merchantPayment = new PaymentMerchant(payment);
		if (merchantReport.containsKey(merchantPayment.getMerchantId())) {
			merchantReport.get(merchantPayment.getMerchantId()).add(merchantPayment);
		}
		else {
			var paymentSet = new HashSet<PaymentMerchant>();
			paymentSet.add(merchantPayment);
			merchantReport.put(merchantPayment.getMerchantId(), paymentSet);
		}
		
	}
	
//	public String getCustomerReport(String customerId) {
//		String reportAsJsonString = gson.toJson(customerReport.get(customerId));
//		return reportAsJsonString;
//	}
//	
//	public String getMerchantReport(String merchantId) {
//		String reportAsJsonString = gson.toJson(merchantReport.get(merchantId));
//		return reportAsJsonString;
//	}
//
//	public String getManagerReport() {
//		String reportAsJsonString = gson.toJson(managerReport);
//		return reportAsJsonString;
//	}
	
	
	
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
