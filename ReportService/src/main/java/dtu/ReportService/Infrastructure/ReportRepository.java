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
	HashMap<String, ArrayList<Payment>> customerReport = new HashMap<>();
	// 		mId		set of payments for merchant
	HashMap<String, ArrayList<PaymentMerchant>> merchantReport = new HashMap<>();
	// 		tId		set all payments
	ArrayList<Payment> managerReport = new ArrayList<>();

	
	public ArrayList<Payment> getCustomerPayments(String customerId) {
		if(customerReport.containsKey(customerId)) {
			return customerReport.get(customerId);
		}
		return null;
	}
	
	public ArrayList<PaymentMerchant> getMerchantPayments(String merchantId) {
		if(merchantReport.containsKey(merchantId)) {
			return merchantReport.get(merchantId);
		}
		return null;
	}
	
	public ArrayList<Payment> getManagerPayments() {
		return managerReport;
	}
	
	public void put(Payment payment) {
		managerReport.add(payment);
		
		if (customerReport.containsKey(payment.getCustomerId())) {
			customerReport.get(payment.getCustomerId()).add(payment);
		}
		else {
			var paymentSet = new ArrayList<Payment>();
			paymentSet.add(payment);
			customerReport.put(payment.getCustomerId(), paymentSet);
		}
		
		PaymentMerchant merchantPayment = new PaymentMerchant(payment);
		if (merchantReport.containsKey(merchantPayment.getMerchantId())) {
			merchantReport.get(merchantPayment.getMerchantId()).add(merchantPayment);
		}
		else {
			var paymentSet = new ArrayList<PaymentMerchant>();
			paymentSet.add(merchantPayment);
			merchantReport.put(merchantPayment.getMerchantId(), paymentSet);
		}
		
	}
}
