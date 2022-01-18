package dtu.ReportService.Domain;

public class Payment {
	private String customerId;
	private String merchantId;
	private String token;
	private String amount;
	private Boolean successfulPayment;

	public Payment(String customerId, String merchantId, String token, String amount, Boolean successfulPayment) {
		this.customerId = customerId;
		this.merchantId = merchantId;
		this.token = token;
		this.amount = amount;
		this.successfulPayment = successfulPayment;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Boolean getStatus() {
		return successfulPayment;
	}
}
