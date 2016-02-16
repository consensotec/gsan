package gcom.gui.portal.comum.gateway.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BraspagCancelamento {
	@SerializedName("Status")
	@Expose
	public Integer Status;
	@SerializedName("ReasonCode")
	@Expose
	public Integer ReasonCode;
	@SerializedName("ReasonMessage")
	@Expose
	public String ReasonMessage;
	@SerializedName("ProviderReturnCode")
	@Expose
	public String ProviderReturnCode;
	@SerializedName("ProviderReturnMessage")
	@Expose
	public String ProviderReturnMessage;
	@SerializedName("Links")
	@Expose
	public List<Link> Links = new ArrayList<Link>();

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Integer getReasonCode() {
		return ReasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		ReasonCode = reasonCode;
	}

	public String getReasonMessage() {
		return ReasonMessage;
	}

	public void setReasonMessage(String reasonMessage) {
		ReasonMessage = reasonMessage;
	}

	public String getProviderReturnCode() {
		return ProviderReturnCode;
	}

	public void setProviderReturnCode(String providerReturnCode) {
		ProviderReturnCode = providerReturnCode;
	}

	public String getProviderReturnMessage() {
		return ProviderReturnMessage;
	}

	public void setProviderReturnMessage(String providerReturnMessage) {
		ProviderReturnMessage = providerReturnMessage;
	}

	public List<Link> getLinks() {
		return Links;
	}

	public void setLinks(List<Link> links) {
		Links = links;
	}

	@Generated("org.jsonschema2pojo")
	public class Link {
		@SerializedName("Method")
		@Expose
		public String Method;
		@SerializedName("Rel")
		@Expose
		public String Rel;
		@SerializedName("Href")
		@Expose
		public String Href;

		public String getMethod() {
			return Method;
		}

		public void setMethod(String method) {
			Method = method;
		}

		public String getRel() {
			return Rel;
		}

		public void setRel(String rel) {
			Rel = rel;
		}

		public String getHref() {
			return Href;
		}

		public void setHref(String href) {
			Href = href;
		}
	}
}
