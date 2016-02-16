package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioAnaliticoPrevisaoPagamentoCartaoCreditoBean implements RelatorioBean {

	String NSU;
	String matricula;
	String dataVencimento;
	String dataParcelamento;
	String dataPagamento;
	BigDecimal valorGuia;
	BigDecimal valorPagamento;
	
public RelatorioAnaliticoPrevisaoPagamentoCartaoCreditoBean(){
		
	}

	public RelatorioAnaliticoPrevisaoPagamentoCartaoCreditoBean(
			String NSU,
			String matricula,
			String dataVencimento,
			String dataParcelamento,
			String dataPagamento,
			BigDecimal valorGuia,
			BigDecimal valorPagamento) {

		this.NSU = NSU;
		this.matricula = matricula;
		this.dataVencimento = dataVencimento;
		this.dataParcelamento = dataParcelamento;
		this.dataPagamento = dataPagamento;
		this.valorGuia = valorGuia;
		this.valorPagamento = valorPagamento;
		

	}


	public String getNSU() {
		return NSU;
	}


	public void setNSU(String nSU) {
		NSU = nSU;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public String getDataParcelamento() {
		return dataParcelamento;
	}


	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}


	public String getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public BigDecimal getValorGuia() {
		return valorGuia;
	}


	public void setValorGuia(BigDecimal valorGuia) {
		this.valorGuia = valorGuia;
	}


	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}


	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
}

