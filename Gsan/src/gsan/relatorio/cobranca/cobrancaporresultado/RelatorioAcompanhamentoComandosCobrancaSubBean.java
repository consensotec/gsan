package gsan.relatorio.cobranca.cobrancaporresultado;

import java.math.BigDecimal;

import gsan.relatorio.RelatorioBean;

public class RelatorioAcompanhamentoComandosCobrancaSubBean implements RelatorioBean {	

	private String faixa;
	
	private Integer quantidadeContas;
	
	private Integer quantidadeClientes;
	
	private BigDecimal valorTotal;
	
	public RelatorioAcompanhamentoComandosCobrancaSubBean(
			String faixa, Integer quantidadeContas, Integer quantidadeClientes, BigDecimal valorTotal) {
		super();
		this.faixa = faixa;
		this.quantidadeContas = quantidadeContas;
		this.quantidadeClientes = quantidadeClientes;
		this.valorTotal = valorTotal;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Integer getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(Integer quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
