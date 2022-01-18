package dtu.ReportService.Domain;
public class Payment {
	
	private String customerId;
	private String merchantId;
	private String token;
	private String amount;

	public Payment(String customerId, String merchantId, String token, String amount) {
		this.customerId = customerId;
		this.merchantId = merchantId;
		this.token = token;
		this.amount = amount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getToken() {
		return token;
	}

	public String getAmount() {
		return amount;
	}
}
