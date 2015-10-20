package gcom.gui.faturamento.contratodemanda;

import org.apache.struts.action.ActionForm;

/**
 * [UC1226] Incluir Contrato de Demanda
 * 
 * @author Diogo Peixoto
 * @since 19/09/2011
 *
 */
public class InserirContratoDemandaCondominiosResidenciaisActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String numeroContrato;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String dataInicioContrato;
	private String dataFimContrato;
	private String idCliente;
	private String nomeCliente;
	private String tipoRelacaoCliente;
	private String demandaMinima;
	private String desconto;
	
	public InserirContratoDemandaCondominiosResidenciaisActionForm(){
		
	}
	
	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
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

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
}