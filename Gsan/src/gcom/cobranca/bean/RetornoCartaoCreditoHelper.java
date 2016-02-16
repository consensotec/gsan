package gcom.cobranca.bean;

import java.io.Serializable;

public class RetornoCartaoCreditoHelper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idVenda;

 	private String numeroIdentificacaoTransacao;
 	
 	private String valorTransacao;
 	
 	private String qtdParcelas;
 	
 	private String nomeCliente;

 	private String cpfCliente;
 	
 	private String numeroPedido;
 	
 	private String codigoComprovanteVenda;

 	private String identificacaoRequisicao;

 	private String identificacaoPagamento;

	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public String getNumeroIdentificacaoTransacao() {
		return numeroIdentificacaoTransacao;
	}
	public void setNumeroIdentificacaoTransacao(String numeroIdentificacaoTransacao) {
		this.numeroIdentificacaoTransacao = numeroIdentificacaoTransacao;
	}
	public String getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public String getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getCodigoComprovanteVenda() {
		return codigoComprovanteVenda;
	}
	public void setCodigoComprovanteVenda(String codigoComprovanteVenda) {
		this.codigoComprovanteVenda = codigoComprovanteVenda;
	}
	public String getIdentificacaoRequisicao() {
		return identificacaoRequisicao;
	}
	public void setIdentificacaoRequisicao(String identificacaoRequisicao) {
		this.identificacaoRequisicao = identificacaoRequisicao;
	}
	public String getIdentificacaoPagamento() {
		return identificacaoPagamento;
	}
	public void setIdentificacaoPagamento(String identificacaoPagamento) {
		this.identificacaoPagamento = identificacaoPagamento;
	}
}
