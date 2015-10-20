package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteFoneVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteVisitaCampo;

import java.io.Serializable;

public class ArquivosClienteHelper implements Serializable {

	private static final long serialVersionUID = 869108066704850934L;
	
	private String idOrdemServico;
	
	private ArquivoTextoRetornoClienteVisitaCampo arquivoClientes;
	
	private ArquivoTextoRetornoClienteFoneVisitaCampo arquivoClientesFone;

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public ArquivoTextoRetornoClienteVisitaCampo getArquivoClientes() {
		return arquivoClientes;
	}

	public void setArquivoClientes(ArquivoTextoRetornoClienteVisitaCampo arquivoClientes) {
		this.arquivoClientes = arquivoClientes;
	}

	public ArquivoTextoRetornoClienteFoneVisitaCampo getArquivoClientesFone() {
		return arquivoClientesFone;
	}

	public void setArquivoClientesFone(ArquivoTextoRetornoClienteFoneVisitaCampo arquivoClientesFone) {
		this.arquivoClientesFone = arquivoClientesFone;
	}
	
}
