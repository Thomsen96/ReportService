package dtu.ReportService.Domain;

public class PaymentMerchant {
	private String merchantId;
	private String token;
	private String amount;

	public PaymentMerchant(Payment payment) {
		this.merchantId = payment.getMerchantId();
		this.token = payment.getToken();
		this.amount = payment.getAmount();
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
