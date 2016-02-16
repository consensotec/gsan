package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioSinteticoPrevisaoPagamentoCartaoCreditoBean implements RelatorioBean {

	String dataVencimento;
	BigDecimal valorGuia;
	BigDecimal valorTarifa;
	BigDecimal valorCreditado;
	
	public RelatorioSinteticoPrevisaoPagamentoCartaoCreditoBean(){
		
	}
	
	public RelatorioSinteticoPrevisaoPagamentoCartaoCreditoBean(
			String dataVencimento, BigDecimal valorGuia, BigDecimal valorTarifa,
			BigDecimal valorCreditado) {
		super();
		this.dataVencimento = dataVencimento;
		this.valorGuia = valorGuia;
		this.valorTarifa = valorTarifa;
		this.valorCreditado = valorCreditado;
	}
	
	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(BigDecimal valorGuia) {
		this.valorGuia = valorGuia;
	}

	public BigDecimal getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public BigDecimal getValorCreditado() {
		return valorCreditado;
	}

	public void setValorCreditado(BigDecimal valorCreditado) {
		this.valorCreditado = valorCreditado;
	}


}