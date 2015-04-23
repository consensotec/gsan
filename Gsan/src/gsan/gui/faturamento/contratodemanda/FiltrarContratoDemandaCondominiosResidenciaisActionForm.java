package gsan.gui.faturamento.contratodemanda;

import org.apache.struts.action.ActionForm;

/**
 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 23/09/2011
 *
 */
public class FiltrarContratoDemandaCondominiosResidenciaisActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String dataInicioContrato;
	private String dataInicioContrato2;
	private String dataFimContrato;
	private String dataFimContrato2;
	private String numeroContrato;
	private String indicadorAtualizar;
	
	public FiltrarContratoDemandaCondominiosResidenciaisActionForm(){
		
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getDataInicioContrato() {
		return dataInicioContrato;
	}
	public void setDataInicioContrato(String dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	public String getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(String dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getDataInicioContrato2() {
		return dataInicioContrato2;
	}
	public void setDataInicioContrato2(String dataInicioContrato2) {
		this.dataInicioContrato2 = dataInicioContrato2;
	}
	public String getDataFimContrato2() {
		return dataFimContrato2;
	}
	public void setDataFimContrato2(String dataFimContrato2) {
		this.dataFimContrato2 = dataFimContrato2;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
}