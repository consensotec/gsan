package gcom.atendimentopublico.bean;

import java.io.Serializable;

public class AcoesParaCorrecaoAnormalidadesEncontradasHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private String idServicoTipo;
	
	private String servicoTipo;

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	

}
