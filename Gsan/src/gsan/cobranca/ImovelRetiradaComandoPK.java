package gsan.cobranca;

import java.io.Serializable;

public class ImovelRetiradaComandoPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idImovel;
	private Integer idComando;
	
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getIdComando() {
		return idComando;
	}
	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}
	
	

	
}
