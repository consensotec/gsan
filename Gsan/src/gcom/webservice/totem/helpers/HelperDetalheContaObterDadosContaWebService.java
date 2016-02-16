package gcom.webservice.totem.helpers;

import com.google.gson.annotations.SerializedName;

public class HelperDetalheContaObterDadosContaWebService {
	
	@SerializedName("a")
	String descricao;
	@SerializedName("b")
	String valor;
	@SerializedName("c")
	String faixa;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getFaixa() {
		return faixa;
	}
	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}
	

}
