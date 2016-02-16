package gcom.gui.portal.comum.gateway.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BraspagErro {
	@SerializedName("Code")
	@Expose
	public Integer Code;
	@SerializedName("Message")
	@Expose
	public String Message;

	public Integer getCode() {
		return Code;
	}

	public void setCode(Integer code) {
		Code = code;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Override
	public String toString() {
		return "Code=" + Code + ", Message=" + Message;
	}
}
