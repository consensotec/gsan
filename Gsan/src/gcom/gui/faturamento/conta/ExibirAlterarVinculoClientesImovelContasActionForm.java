package gcom.gui.faturamento.conta;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

/**
 * [UC 1366] - Alterar Vínculo de Clientes com Imóvel e Contas
 * 
 * @author Rafael Corrêa
 * @date 24/04/2015
 *
 */
public class ExibirAlterarVinculoClientesImovelContasActionForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String inscricaoImovel;
	
	private String nomeClienteUsuarioAtual;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String idCliente;
	
	private String nomeCliente;
	
	private String tipoRelacao;
	
	private String dataInicio;
	
	private String dataFinal;
	
	private String motivoFim;
	
	private String indicadorNomeConta;
	
	private String[] idsRegistro;
	
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

	public String getNomeClienteUsuarioAtual() {
		return nomeClienteUsuarioAtual;
	}

	public void setNomeClienteUsuarioAtual(String nomeClienteUsuarioAtual) {
		this.nomeClienteUsuarioAtual = nomeClienteUsuarioAtual;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(String indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public String[] getIdsRegistro() {
		return idsRegistro;
	}

	public void setIdsRegistro(String[] idsRegistro) {
		this.idsRegistro = idsRegistro;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getMotivoFim() {
		return motivoFim;
	}

	public void setMotivoFim(String motivoFim) {
		this.motivoFim = motivoFim;
	}

	public void reset(){
		this.idImovel = "";
		this.inscricaoImovel = "";
		this.idCliente = "";
		this.nomeCliente = "";
		this.nomeClienteUsuarioAtual = "";
		this.situacaoAgua = "";
		this.situacaoEsgoto = "";
		this.tipoRelacao = "-1";
		this.dataInicio = "";
		this.dataFinal = "";
		this.motivoFim = "-1";
		this.indicadorNomeConta = "";
		this.idsRegistro = null;
	}
	
	public void limparCamposCliente(){
		this.idCliente = "";
		this.nomeCliente = "";
		this.tipoRelacao = "-1";
		this.dataInicio = "";
		this.dataFinal = "";
		this.motivoFim = "-1";
		this.indicadorNomeConta = "";
	}
	
}
