package gsan.gui.faturamento.contratodemanda;

import java.util.Date;

/**
 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
 * 
 * Classe responsável por auxiliar na montagem das tabelas da tela
 * contrato_demanda_condominios_manter.jsp
 * 
 * @author Diogo Peixoto
 * @since 23/09/2011
 *
 */
public class ContratoDemandaCondominiosResidenciaisHelper {

	private String numeroContrato;
	private Integer idImovel;
	private Date dataInicio;
	private Date dataFim;
	private String cliente;
	private Integer desconto;
	private String situacao;
	
	/**
	 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
	 * 
	 * @param numeroContrato
	 * @param idImovel
	 * @param dataInicio
	 * @param dataFim
	 * @param cliente
	 * @param descont
	 * @param situacao
	 */
	public ContratoDemandaCondominiosResidenciaisHelper(String numeroContrato, Integer idImovel, 
			Date dataInicio, Date dataFim, String cliente, Integer desconto, String situacao){
		this.numeroContrato = numeroContrato;
		this.idImovel = idImovel;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.cliente = cliente;
		this.desconto = desconto;
		this.situacao = situacao;
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
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}