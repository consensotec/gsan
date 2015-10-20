package gcom.faturamento;

import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class FaturamentoGrupoCanceladoHistorico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer anoMesReferencia;

	private Integer qtdContasSituacaoNormal;
	
	private Integer qtdContasSituacaoIncluida;
	
	private Integer qtdContasSituacaoCancelada;
	
	private Integer qtdContasSituacaoRetificada;
	
	private Integer qtdContasSituacaoPaga;
	
	private Integer qtdContasSituacaoParceladas;
	
	private Integer qtdContasDocumentoCobranca; 

	private Integer qtdContasFaturaItem;
	
	private Integer qtdContasExcluidas;
	
	private BigDecimal valorAguaFaturado;
	
	private BigDecimal valorEsgotoFaturado;
	
	private BigDecimal valorDebitoFaturado;
	
	private BigDecimal valorCreditoFaturado;
	
	private BigDecimal valorImpostosFaturado;
	
	private Timestamp dataCancelamento;
	
	private Timestamp ultimaAlteracao;
	
	private Usuario usuario;
	
	private ContaMotivoCancelamento contaMotivoCancelamento;
	
	private FaturamentoGrupo faturamentoGrupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getQtdContasSituacaoNormal() {
		return qtdContasSituacaoNormal;
	}

	public void setQtdContasSituacaoNormal(Integer qtdContasSituacaoNormal) {
		this.qtdContasSituacaoNormal = qtdContasSituacaoNormal;
	}

	public Integer getQtdContasSituacaoIncluida() {
		return qtdContasSituacaoIncluida;
	}

	public void setQtdContasSituacaoIncluida(Integer qtdContasSituacaoIncluida) {
		this.qtdContasSituacaoIncluida = qtdContasSituacaoIncluida;
	}

	public Integer getQtdContasSituacaoCancelada() {
		return qtdContasSituacaoCancelada;
	}

	public void setQtdContasSituacaoCancelada(Integer qtdContasSituacaoCancelada) {
		this.qtdContasSituacaoCancelada = qtdContasSituacaoCancelada;
	}

	public Integer getQtdContasSituacaoRetificada() {
		return qtdContasSituacaoRetificada;
	}

	public void setQtdContasSituacaoRetificada(Integer qtdContasSituacaoRetificada) {
		this.qtdContasSituacaoRetificada = qtdContasSituacaoRetificada;
	}

	public Integer getQtdContasSituacaoPaga() {
		return qtdContasSituacaoPaga;
	}

	public void setQtdContasSituacaoPaga(Integer qtdContasSituacaoPaga) {
		this.qtdContasSituacaoPaga = qtdContasSituacaoPaga;
	}

	public Integer getQtdContasSituacaoParceladas() {
		return qtdContasSituacaoParceladas;
	}

	public void setQtdContasSituacaoParceladas(Integer qtdContasSituacaoParceladas) {
		this.qtdContasSituacaoParceladas = qtdContasSituacaoParceladas;
	}

	public Integer getQtdContasDocumentoCobranca() {
		return qtdContasDocumentoCobranca;
	}

	public void setQtdContasDocumentoCobranca(Integer qtdContasDocumentoCobranca) {
		this.qtdContasDocumentoCobranca = qtdContasDocumentoCobranca;
	}

	public Integer getQtdContasFaturaItem() {
		return qtdContasFaturaItem;
	}

	public void setQtdContasFaturaItem(Integer qtdContasFaturaItem) {
		this.qtdContasFaturaItem = qtdContasFaturaItem;
	}

	public Integer getQtdContasExcluidas() {
		return qtdContasExcluidas;
	}

	public void setQtdContasExcluidas(Integer qtdContasExcluidas) {
		this.qtdContasExcluidas = qtdContasExcluidas;
	}

	public BigDecimal getValorAguaFaturado() {
		return valorAguaFaturado;
	}

	public void setValorAguaFaturado(BigDecimal valorAguaFaturado) {
		this.valorAguaFaturado = valorAguaFaturado;
	}

	public BigDecimal getValorEsgotoFaturado() {
		return valorEsgotoFaturado;
	}

	public void setValorEsgotoFaturado(BigDecimal valorEsgotoFaturado) {
		this.valorEsgotoFaturado = valorEsgotoFaturado;
	}

	public BigDecimal getValorDebitoFaturado() {
		return valorDebitoFaturado;
	}

	public void setValorDebitoFaturado(BigDecimal valorDebitoFaturado) {
		this.valorDebitoFaturado = valorDebitoFaturado;
	}

	public BigDecimal getValorCreditoFaturado() {
		return valorCreditoFaturado;
	}

	public void setValorCreditoFaturado(BigDecimal valorCreditoFaturado) {
		this.valorCreditoFaturado = valorCreditoFaturado;
	}

	public BigDecimal getValorImpostosFaturado() {
		return valorImpostosFaturado;
	}

	public void setValorImpostosFaturado(BigDecimal valorImpostosFaturado) {
		this.valorImpostosFaturado = valorImpostosFaturado;
	}

	public Timestamp getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Timestamp dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Timestamp getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Timestamp ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ContaMotivoCancelamento getContaMotivoCancelamento() {
		return contaMotivoCancelamento;
	}

	public void setContaMotivoCancelamento(ContaMotivoCancelamento contaMotivoCancelamento) {
		this.contaMotivoCancelamento = contaMotivoCancelamento;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	

}
