package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoAcaoVisitaCampo;

import java.io.Serializable;

public class ArquivoRetornoAcoesHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	
	private ArquivoTextoRetornoAcaoVisitaCampo arquivoRetornoAcao;

	public ArquivoTextoRetornoAcaoVisitaCampo getArquivoRetornoAcao() {
		return arquivoRetornoAcao;
	}

	public void setArquivoRetornoAcao(ArquivoTextoRetornoAcaoVisitaCampo arquivoRetornoAcao) {
		this.arquivoRetornoAcao = arquivoRetornoAcao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	
}
