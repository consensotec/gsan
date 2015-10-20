package gcom.cobranca.bean;

import java.io.Serializable;

public class ContasRevisaoEntradaParcelamentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idConta;
	
	private Integer idDocumentoCobranca;

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}
	
}
