package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC 1370] Consultar Ações de Cobrança por Imóvel
 * 
 * @author Davi Menezes
 * @date 15/08/2012
 *
 */
public class AcoesCobrancaImovelHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricaoAcaoCobranca;
	
	private String nomeAcaoCobranca;
	
	private String grupoCobranca;
	
	private String mesAnoCronograma;
	
	private String dataExecucao;
	
	private Integer idCobrancaDocumento;
	
	private BigDecimal valorDocumento;
	
	private String dataVencimento;
	
	private Integer idSituacaoCobrancaDebito;
	
	private String descricaoSituacaoDebito;
	
	private String dataSituacao;
	
	private Integer idCobrancaAcaoAtividadeCronograma;
	
	private Integer idCobrancaAcaoAtividadeComando;
	
	private BigDecimal valorTaxaServico;
	
	private BigDecimal valorTaxaSucesso;
	
	private String numeroOS;
	
	private String situacaoOS;
	
	private String motivoEncerramentoOS;
	
	private String dataEncerramentoOS;
	
	private String usuarioEncerramentoOS;
	
	private String empresaEncerramentoOS;
	
	private String observacoesOS;
	
	private String fiscalizacaoOS;
	
	private String exibirPenalidade;
	
	private String descricaoFiscalizacaoSituacao;
	
	private Collection<AcoesCobrancaTipoDebitoHelper> colTipoDebito;

	public String getDescricaoAcaoCobranca() {
		return descricaoAcaoCobranca;
	}

	public void setDescricaoAcaoCobranca(String descricaoAcaoCobranca) {
		this.descricaoAcaoCobranca = descricaoAcaoCobranca;
	}

	public String getNomeAcaoCobranca() {
		return nomeAcaoCobranca;
	}

	public void setNomeAcaoCobranca(String nomeAcaoCobranca) {
		this.nomeAcaoCobranca = nomeAcaoCobranca;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getMesAnoCronograma() {
		return mesAnoCronograma;
	}

	public void setMesAnoCronograma(String mesAnoCronograma) {
		this.mesAnoCronograma = mesAnoCronograma;
	}

	public String getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Integer getIdCobrancaDocumento() {
		return idCobrancaDocumento;
	}

	public void setIdCobrancaDocumento(Integer idCobrancaDocumento) {
		this.idCobrancaDocumento = idCobrancaDocumento;
	}
	
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getIdSituacaoCobrancaDebito() {
		return idSituacaoCobrancaDebito;
	}

	public void setIdSituacaoCobrancaDebito(Integer idSituacaoCobrancaDebito) {
		this.idSituacaoCobrancaDebito = idSituacaoCobrancaDebito;
	}

	public String getDescricaoSituacaoDebito() {
		return descricaoSituacaoDebito;
	}

	public void setDescricaoSituacaoDebito(String descricaoSituacaoDebito) {
		this.descricaoSituacaoDebito = descricaoSituacaoDebito;
	}

	public String getDataSituacao() {
		return dataSituacao;
	}

	public void setDataSituacao(String dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	public Integer getIdCobrancaAcaoAtividadeCronograma() {
		return idCobrancaAcaoAtividadeCronograma;
	}

	public void setIdCobrancaAcaoAtividadeCronograma(
			Integer idCobrancaAcaoAtividadeCronograma) {
		this.idCobrancaAcaoAtividadeCronograma = idCobrancaAcaoAtividadeCronograma;
	}

	public Integer getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(
			Integer idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}
	
	public BigDecimal getValorTaxaServico() {
		return valorTaxaServico;
	}

	public void setValorTaxaServico(BigDecimal valorTaxaServico) {
		this.valorTaxaServico = valorTaxaServico;
	}

	public BigDecimal getValorTaxaSucesso() {
		return valorTaxaSucesso;
	}

	public void setValorTaxaSucesso(BigDecimal valorTaxaSucesso) {
		this.valorTaxaSucesso = valorTaxaSucesso;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	public String getMotivoEncerramentoOS() {
		return motivoEncerramentoOS;
	}

	public void setMotivoEncerramentoOS(String motivoEncerramentoOS) {
		this.motivoEncerramentoOS = motivoEncerramentoOS;
	}

	public String getDataEncerramentoOS() {
		return dataEncerramentoOS;
	}

	public void setDataEncerramentoOS(String dataEncerramentoOS) {
		this.dataEncerramentoOS = dataEncerramentoOS;
	}

	public String getUsuarioEncerramentoOS() {
		return usuarioEncerramentoOS;
	}

	public void setUsuarioEncerramentoOS(String usuarioEncerramentoOS) {
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
	}

	public String getEmpresaEncerramentoOS() {
		return empresaEncerramentoOS;
	}

	public void setEmpresaEncerramentoOS(String empresaEncerramentoOS) {
		this.empresaEncerramentoOS = empresaEncerramentoOS;
	}

	public String getObservacoesOS() {
		return observacoesOS;
	}

	public void setObservacoesOS(String observacoesOS) {
		this.observacoesOS = observacoesOS;
	}
	
	public String getFiscalizacaoOS() {
		return fiscalizacaoOS;
	}

	public void setFiscalizacaoOS(String fiscalizacaoOS) {
		this.fiscalizacaoOS = fiscalizacaoOS;
	}

	public String getExibirPenalidade() {
		return exibirPenalidade;
	}

	public void setExibirPenalidade(String exibirPenalidade) {
		this.exibirPenalidade = exibirPenalidade;
	}

	public String getDescricaoFiscalizacaoSituacao() {
		return descricaoFiscalizacaoSituacao;
	}

	public void setDescricaoFiscalizacaoSituacao(String descricaoFiscalizacaoSituacao) {
		this.descricaoFiscalizacaoSituacao = descricaoFiscalizacaoSituacao;
	}

	public Collection<AcoesCobrancaTipoDebitoHelper> getColTipoDebito() {
		return colTipoDebito;
	}

	public void setColTipoDebito(Collection<AcoesCobrancaTipoDebitoHelper> colTipoDebito) {
		this.colTipoDebito = colTipoDebito;
	}
	
}
