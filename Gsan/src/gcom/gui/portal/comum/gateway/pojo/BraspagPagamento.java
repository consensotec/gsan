package gcom.gui.portal.comum.gateway.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Representa a resposta da chamada BraspagServiceQuery#getPagamento.
 * @author André Miranda
 *
 */
@Generated("org.jsonschema2pojo")
public class BraspagPagamento {
	@SerializedName("MerchantOrderId")
	@Expose
	private String MerchantOrderId;
	@SerializedName("Customer")
	@Expose
	private Customer customer;
	@SerializedName("Payment")
	@Expose
	private Payment payment;

	public String getMerchantOrderId() {
		return MerchantOrderId;
	}

	public void setMerchantOrderId(String MerchantOrderId) {
		this.MerchantOrderId = MerchantOrderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer Customer) {
		this.customer = Customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment Payment) {
		this.payment = Payment;
	}

	@Generated("org.jsonschema2pojo")
	public class CreditCard {
		@SerializedName("CardNumber")
		@Expose
		private String CardNumber;
		@SerializedName("Holder")
		@Expose
		private String Holder;
		@SerializedName("ExpirationDate")
		@Expose
		private String ExpirationDate;
		@SerializedName("Brand")
		@Expose
		private String Brand;

		public String getCardNumber() {
			return CardNumber;
		}

		public void setCardNumber(String CardNumber) {
			this.CardNumber = CardNumber;
		}

		public String getHolder() {
			return Holder;
		}

		public void setHolder(String Holder) {
			this.Holder = Holder;
		}

		public String getExpirationDate() {
			return ExpirationDate;
		}

		public void setExpirationDate(String ExpirationDate) {
			this.ExpirationDate = ExpirationDate;
		}

		public String getBrand() {
			return Brand;
		}

		public void setBrand(String Brand) {
			this.Brand = Brand;
		}
	}

	@Generated("org.jsonschema2pojo")
	public class Customer {
		@SerializedName("Name")
		@Expose
		private String Name;
		@SerializedName("Identity")
		@Expose
		private String Identity;

		public String getName() {
			return Name;
		}

		public void setName(String Name) {
			this.Name = Name;
		}

		public String getIdentity() {
			return Identity;
		}

		public void setIdentity(String Identity) {
			this.Identity = Identity;
		}
	}

	@Generated("org.jsonschema2pojo")
	public class PagamentoBraspag {
		@SerializedName("MerchantOrderId")
		@Expose
		private String MerchantOrderId;
		@SerializedName("Customer")
		@Expose
		private Customer customer;
		@SerializedName("Payment")
		@Expose
		private Payment payment;

		public String getMerchantOrderId() {
			return MerchantOrderId;
		}

		public void setMerchantOrderId(String MerchantOrderId) {
			this.MerchantOrderId = MerchantOrderId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer Customer) {
			this.customer = Customer;
		}

		public Payment getPayment() {
			return payment;
		}

		public void setPayment(Payment Payment) {
			this.payment = Payment;
		}
	}

	@Generated("org.jsonschema2pojo")
	public class Link {
		@SerializedName("Method")
		@Expose
		private String Method;
		@SerializedName("Rel")
		@Expose
		private String Rel;
		@SerializedName("Href")
		@Expose
		private String Href;

		public String getMethod() {
			return Method;
		}

		public void setMethod(String Method) {
			this.Method = Method;
		}

		public String getRel() {
			return Rel;
		}

		public void setRel(String Rel) {
			this.Rel = Rel;
		}

		public String getHref() {
			return Href;
		}

		public void setHref(String Href) {
			this.Href = Href;
		}
	}

	@Generated("org.jsonschema2pojo")
	public class Payment {
		@SerializedName("ServiceTaxAmount")
		@Expose
		private Integer ServiceTaxAmount;
		@SerializedName("Installments")
		@Expose
		private Integer Installments;
		@SerializedName("Interest")
		@Expose
		private String Interest;
		@SerializedName("Capture")
		@Expose
		private Boolean Capture;
		@SerializedName("Authenticate")
		@Expose
		private Boolean Authenticate;
		@SerializedName("CreditCard")
		@Expose
		private CreditCard creditCard;
		@SerializedName("ProofOfSale")
		@Expose
		private String ProofOfSale;
		@SerializedName("AcquirerTransactionId")
		@Expose
		private String AcquirerTransactionId;
		@SerializedName("AuthorizationCode")
		@Expose
		private String AuthorizationCode;
		@SerializedName("PaymentId")
		@Expose
		private String PaymentId;
		@SerializedName("Type")
		@Expose
		private String Type;
		@SerializedName("Amount")
		@Expose
		private Integer Amount;
		@SerializedName("ReceivedDate")
		@Expose
		private String ReceivedDate;
		@SerializedName("CapturedDate")
		@Expose
		private String CapturedDate;
		@SerializedName("Currency")
		@Expose
		private String Currency;
		@SerializedName("Country")
		@Expose
		private String Country;
		@SerializedName("Provider")
		@Expose
		private String Provider;
		@SerializedName("ReasonCode")
		@Expose
		private Integer ReasonCode;
		@SerializedName("Status")
		@Expose
		private Integer Status;
		@SerializedName("Links")
		@Expose
		private List<Link> Links = new ArrayList<Link>();

		public Integer getServiceTaxAmount() {
			return ServiceTaxAmount;
		}

		public void setServiceTaxAmount(Integer ServiceTaxAmount) {
			this.ServiceTaxAmount = ServiceTaxAmount;
		}

		public Integer getInstallments() {
			return Installments;
		}

		public void setInstallments(Integer Installments) {
			this.Installments = Installments;
		}

		public String getInterest() {
			return Interest;
		}

		public void setInterest(String Interest) {
			this.Interest = Interest;
		}

		public Boolean getCapture() {
			return Capture;
		}

		public void setCapture(Boolean Capture) {
			this.Capture = Capture;
		}

		public Boolean getAuthenticate() {
			return Authenticate;
		}

		public void setAuthenticate(Boolean Authenticate) {
			this.Authenticate = Authenticate;
		}

		public CreditCard getCreditCard() {
			return creditCard;
		}

		public void setCreditCard(CreditCard CreditCard) {
			this.creditCard = CreditCard;
		}

		public String getProofOfSale() {
			return ProofOfSale;
		}

		public void setProofOfSale(String ProofOfSale) {
			this.ProofOfSale = ProofOfSale;
		}

		public String getAcquirerTransactionId() {
			return AcquirerTransactionId;
		}

		public void setAcquirerTransactionId(String AcquirerTransactionId) {
			this.AcquirerTransactionId = AcquirerTransactionId;
		}

		public String getAuthorizationCode() {
			return AuthorizationCode;
		}

		public void setAuthorizationCode(String AuthorizationCode) {
			this.AuthorizationCode = AuthorizationCode;
		}

		public String getPaymentId() {
			return PaymentId;
		}

		public void setPaymentId(String PaymentId) {
			this.PaymentId = PaymentId;
		}

		public String getType() {
			return Type;
		}

		public void setType(String Type) {
			this.Type = Type;
		}

		public Integer getAmount() {
			return Amount;
		}

		public void setAmount(Integer Amount) {
			this.Amount = Amount;
		}

		public String getReceivedDate() {
			return ReceivedDate;
		}

		public void setReceivedDate(String ReceivedDate) {
			this.ReceivedDate = ReceivedDate;
		}

		public String getCapturedDate() {
			return CapturedDate;
		}

		public void setCapturedDate(String CapturedDate) {
			this.CapturedDate = CapturedDate;
		}

		public String getCurrency() {
			return Currency;
		}

		public void setCurrency(String Currency) {
			this.Currency = Currency;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String Country) {
			this.Country = Country;
		}

		public String getProvider() {
			return Provider;
		}

		public void setProvider(String Provider) {
			this.Provider = Provider;
		}

		public Integer getReasonCode() {
			return ReasonCode;
		}

		public void setReasonCode(Integer ReasonCode) {
			this.ReasonCode = ReasonCode;
		}

		public Integer getStatus() {
			return Status;
		}

		public void setStatus(Integer Status) {
			this.Status = Status;
		}

		public List<Link> getLinks() {
			return Links;
		}

		public void setLinks(List<Link> Links) {
			this.Links = Links;
		}
	}
}
