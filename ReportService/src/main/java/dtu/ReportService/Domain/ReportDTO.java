package dtu.ReportService.Domain;

import java.util.ArrayList;

public class ReportDTO {

  public static class CustomerPayment {
    public String customerId;
    public String merchantId;
    public String token;
    public String amount;
    public CustomerPayment(String customerId, String merchantId, String token, String amount) {
      this.customerId = customerId;
      this.merchantId = merchantId;
      this.token = token;
      this.amount = amount;
    }
    public CustomerPayment() {
    }
    @Override
    public String toString() {
      return "CustomerPayment [amount=" + amount + ", customerId=" + customerId + ", merchantId=" + merchantId
          + ", token=" + token + "]";
    }
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((amount == null) ? 0 : amount.hashCode());
      result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
      result = prime * result + ((merchantId == null) ? 0 : merchantId.hashCode());
      result = prime * result + ((token == null) ? 0 : token.hashCode());
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      CustomerPayment other = (CustomerPayment) obj;
      if (amount == null) {
        if (other.amount != null)
          return false;
      } else if (!amount.equals(other.amount))
        return false;
      if (customerId == null) {
        if (other.customerId != null)
          return false;
      } else if (!customerId.equals(other.customerId))
        return false;
      if (merchantId == null) {
        if (other.merchantId != null)
          return false;
      } else if (!merchantId.equals(other.merchantId))
        return false;
      if (token == null) {
        if (other.token != null)
          return false;
      } else if (!token.equals(other.token))
        return false;
      return true;
    }
  }

  public static class Customer {
    ArrayList<CustomerPayment> payments;

    public Customer(ArrayList<CustomerPayment> payments) {
      this.payments = payments;
    }

    public Customer() {
    }

    @Override
    public String toString() {
      return "Customer [payments=" + payments + "]";
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((payments == null) ? 0 : payments.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Customer other = (Customer) obj;
      if (payments == null) {
        if (other.payments != null)
          return false;
      } else if (!payments.equals(other.payments))
        return false;
      return true;
    }
  }
  

  public static class MerchantPayment {
    public String merchantId;
    public String token;
    public String amount;
    public MerchantPayment(String merchantId, String token, String amount) {
      this.merchantId = merchantId;
      this.token = token;
      this.amount = amount;
    }
    public MerchantPayment() {
    }
    @Override
    public String toString() {
      return "MerchantPayment [amount=" + amount + ", merchantId=" + merchantId
          + ", token=" + token + "]";
    }
  }

  public static class Merchant {
    ArrayList<MerchantPayment> payments;
  }
  
}
