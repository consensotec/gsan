package gcom.cobranca;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class EmpresaCobrancaContaPenalidade implements Serializable{

	/**
	 * @author Raimundo Martins
	 */
	private static final long serialVersionUID = 1L;
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	private Integer qtdImoveisEnviados;
	private Integer qtdImovesOSGeradas;
	private Integer qtdOsEncerradasDercursoPrazo;
	private Integer qtdFaturasSelecionadasComando;
	private Integer qtdFaturasSelecionadasPagtoGerado;
	private BigDecimal vlTotalContasSelecionadasComando;
	private BigDecimal vlTotalContasSelecionadasPagtoGerado;
	private Date ultimaAlteracao;
	
	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}
	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}
	public Integer getQtdImoveisEnviados() {
		return qtdImoveisEnviados;
	}
	public void setQtdImoveisEnviados(Integer qtdImoveisEnviados) {
		this.qtdImoveisEnviados = qtdImoveisEnviados;
	}
	public Integer getQtdImovesOSGeradas() {
		return qtdImovesOSGeradas;
	}
	public void setQtdImovesOSGeradas(Integer qtdImovesOSGeradas) {
		this.qtdImovesOSGeradas = qtdImovesOSGeradas;
	}
	public Integer getQtdOsEncerradasDercursoPrazo() {
		return qtdOsEncerradasDercursoPrazo;
	}
	public void setQtdOsEncerradasDercursoPrazo(Integer qtdOsEncerradasDercursoPrazo) {
		this.qtdOsEncerradasDercursoPrazo = qtdOsEncerradasDercursoPrazo;
	}
	public Integer getQtdFaturasSelecionadasComando() {
		return qtdFaturasSelecionadasComando;
	}
	public void setQtdFaturasSelecionadasComando(Integer qtdFaturasSelecionadasComando) {
		this.qtdFaturasSelecionadasComando = qtdFaturasSelecionadasComando;
	}
	public Integer getQtdFaturasSelecionadasPagtoGerado() {
		return qtdFaturasSelecionadasPagtoGerado;
	}
	public void setQtdFaturasSelecionadasPagtoGerado(Integer qtdFaturasSelecionadasPagtoGerado) {
		this.qtdFaturasSelecionadasPagtoGerado = qtdFaturasSelecionadasPagtoGerado;
	}
	public BigDecimal getVlTotalContasSelecionadasComando() {
		return vlTotalContasSelecionadasComando;
	}
	public void setVlTotalContasSelecionadasComando(BigDecimal vlTotalContasSelecionadasComando) {
		this.vlTotalContasSelecionadasComando = vlTotalContasSelecionadasComando;
	}
	public BigDecimal getVlTotalContasSelecionadasPagtoGerado() {
		return vlTotalContasSelecionadasPagtoGerado;
	}
	public void setVlTotalContasSelecionadasPagtoGerado(BigDecimal vlTotalContasSelecionadasPagtoGerado) {
		this.vlTotalContasSelecionadasPagtoGerado = vlTotalContasSelecionadasPagtoGerado;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
