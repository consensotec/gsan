package gcom.gui.faturamento.contratodemanda;

import java.util.Date;

public class FiltrarContratoDemandaCondominiosResidenciaisHelper {

	private String numeroContrato;
	private Integer idImovel;
	private Date dataInicioContrato;
	private Date dataInicioContrato2;
	private Date dataFimContrato;
	private Date dataFimContrato2;

	/**
	 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
	 * 
	 * @author Diogo Peixoto
	 * @since 23/09/2011
	 * 
	 * @param numeroContrato
	 * @param idImovel
	 * @param dataInicio
	 * @param dataFim
	 */
	public FiltrarContratoDemandaCondominiosResidenciaisHelper(String numeroContrato, Integer idImovel, Date dataInicioContrato, Date dataInicioContrato2, 
			Date dataFimContrato, Date dataFimContrato2){
		this.numeroContrato = numeroContrato;
		this.idImovel = idImovel;
		this.dataInicioContrato = dataInicioContrato;
		this.dataInicioContrato2 = dataInicioContrato2;
		this.dataFimContrato = dataFimContrato;
		this.dataFimContrato2 = dataFimContrato2;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataInicioContrato2() {
		return dataInicioContrato2;
	}

	public void setDataInicioContrato2(Date dataInicioContrato2) {
		this.dataInicioContrato2 = dataInicioContrato2;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public Date getDataFimContrato2() {
		return dataFimContrato2;
	}

	public void setDataFimContrato2(Date dataFimContrato2) {
		this.dataFimContrato2 = dataFimContrato2;
	}
}