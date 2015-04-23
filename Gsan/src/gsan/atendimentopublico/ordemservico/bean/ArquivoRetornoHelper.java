package gsan.atendimentopublico.ordemservico.bean;

import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoVisitaCampo;

import java.io.Serializable;

public class ArquivoRetornoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	
	private ArquivoTextoRetornoVisitaCampo arquivosRetorno;

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public ArquivoTextoRetornoVisitaCampo getArquivosRetorno() {
		return arquivosRetorno;
	}

	public void setArquivosRetorno(ArquivoTextoRetornoVisitaCampo arquivosRetorno) {
		this.arquivosRetorno = arquivosRetorno;
	}
	
}
