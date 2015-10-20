package gcom.cadastro.cliente.bean;

import java.io.Serializable;

public class ClienteImovelHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idClienteImovel;
	
	private String dataInicioRelacao;
	
	private String dataFimRelacao;
	
	private String idImovel;
	
	private String indicadorNomeConta;
	
	private String indicadorRevisao;
	
	private String idClienteImovelFimRelacaoMotivo;
	
	private String idCliente;
	
	private String nomeCliente;
	
	private String idClienteRelacaoTipo;
	
	private String descricaoRelacaoTipo;
	
	public String getIdClienteImovel() {
		return idClienteImovel;
	}

	public void setIdClienteImovel(String idClienteImovel) {
		this.idClienteImovel = idClienteImovel;
	}

	public String getDataInicioRelacao() {
		return dataInicioRelacao;
	}

	public void setDataInicioRelacao(String dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}

	public String getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(String dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(String indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public String getIdClienteImovelFimRelacaoMotivo() {
		return idClienteImovelFimRelacaoMotivo;
	}

	public void setIdClienteImovelFimRelacaoMotivo(String idClienteImovelFimRelacaoMotivo) {
		this.idClienteImovelFimRelacaoMotivo = idClienteImovelFimRelacaoMotivo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}

	public void setIdClienteRelacaoTipo(String idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescricaoRelacaoTipo() {
		return descricaoRelacaoTipo;
	}

	public void setDescricaoRelacaoTipo(String descricaoRelacaoTipo) {
		this.descricaoRelacaoTipo = descricaoRelacaoTipo;
	}
	
	public String getIndicadorRevisao() {
		return indicadorRevisao;
	}

	public void setIndicadorRevisao(String indicadorRevisao) {
		this.indicadorRevisao = indicadorRevisao;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
            return true;
        }
		
		if (!(other instanceof ClienteImovelHelper)) {
            return false;
        }
		ClienteImovelHelper castOther = (ClienteImovelHelper) other;
		
		boolean ehIgual = 
			
			((this.getIdCliente() == null && castOther.getIdCliente() == null) || 
			(this.getIdCliente() != null && this.getIdCliente().equals(castOther.getIdCliente()))) &&
					
			((this.getIdClienteRelacaoTipo() == null && castOther.getIdClienteRelacaoTipo() == null) || 
			(this.getIdClienteRelacaoTipo() != null && this.getIdClienteRelacaoTipo().equals(castOther.getIdClienteRelacaoTipo()))) &&
			
			((this.getDataInicioRelacao() == null && castOther.getDataInicioRelacao() == null) || 
			(this.getDataInicioRelacao() != null && this.getDataInicioRelacao().equals(castOther.getDataInicioRelacao()))) && 
			
			((this.getDataFimRelacao() == null && castOther.getDataFimRelacao() == null) || 
			(this.getDataFimRelacao() != null && this.getDataFimRelacao().equals(castOther.getDataFimRelacao())));
			
		return ehIgual;
	}
	
}