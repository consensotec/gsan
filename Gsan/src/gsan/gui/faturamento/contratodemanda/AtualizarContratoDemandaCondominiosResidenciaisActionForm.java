package gsan.gui.faturamento.contratodemanda;

import org.apache.struts.action.ActionForm;

/**
 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 26/09/2011
 *
 */
public class AtualizarContratoDemandaCondominiosResidenciaisActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String numeroContrato;
	private String idImovel;
	private String inscricaoImovel;
	private String dataInicio;
	private String dataFim;
	private String idCliente;
	private String nomeCliente;
	private String tipoRelacaoCliente;
	private String demandaMinima;
	private String percentualDesconto;
	private String dataEncerramento;
	private String idMotivoEncerramento;
	private String observacaoEncerramento;
	private String observacaoSuspensao;
	private String situacaoContrato;

	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getTipoRelacaoCliente() {
		return tipoRelacaoCliente;
	}
	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
		this.tipoRelacaoCliente = tipoRelacaoCliente;
	}
	public String getDemandaMinima() {
		return demandaMinima;
	}
	public void setDemandaMinima(String demandaMinima) {
		this.demandaMinima = demandaMinima;
	}
	public String getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(String percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getObservacaoEncerramento() {
		return observacaoEncerramento;
	}
	public void setObservacaoEncerramento(String observacaoEncerramento) {
		this.observacaoEncerramento = observacaoEncerramento;
	}
	public String getObservacaoSuspensao() {
		return observacaoSuspensao;
	}
	public void setObservacaoSuspensao(String observacaoSuspensao) {
		this.observacaoSuspensao = observacaoSuspensao;
	}
	public String getSituacaoContrato() {
		return situacaoContrato;
	}
	public void setSituacaoContrato(String situacaoContrato) {
		this.situacaoContrato = situacaoContrato;
	}
}