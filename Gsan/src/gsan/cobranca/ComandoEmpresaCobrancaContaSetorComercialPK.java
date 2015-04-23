package gsan.cobranca;

import java.io.Serializable;

public class ComandoEmpresaCobrancaContaSetorComercialPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idSetor;
	
	private Integer idComando;

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}
	
	

}
