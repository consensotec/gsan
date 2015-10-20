package gcom.atendimentopublico.bean;

import java.io.Serializable;
import java.util.Collection;

public class ProcessarAtualizacaoOSArquivoTextoHelper implements
	Serializable {
	
	private static final long serialVersionUID = 1L;

	Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> colecaoAcoesCorrecaoAnormalidadesEncontradasHelper;
	
	String[] idsAcoes;
	
	String ordemServicoConferida;
	
	Integer idOrdemServico;
	
	Integer matricula;
	
	Integer idArquivoTexto;

	public Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> getColecaoAcoesCorrecaoAnormalidadesEncontradasHelper() {
		return colecaoAcoesCorrecaoAnormalidadesEncontradasHelper;
	}

	public void setColecaoAcoesCorrecaoAnormalidadesEncontradasHelper(
			Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> colecaoAcoesCorrecaoAnormalidadesEncontradasHelper) {
		this.colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = colecaoAcoesCorrecaoAnormalidadesEncontradasHelper;
	}

	public String[] getIdsAcoes() {
		return idsAcoes;
	}

	public void setIdsAcoes(String[] idsAcoes) {
		this.idsAcoes = idsAcoes;
	}

	public String getOrdemServicoConferida() {
		return ordemServicoConferida;
	}

	public void setOrdemServicoConferida(String ordemServicoConferida) {
		this.ordemServicoConferida = ordemServicoConferida;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getIdArquivoTexto() {
		return idArquivoTexto;
	}

	public void setIdArquivoTexto(Integer idArquivoTexto) {
		this.idArquivoTexto = idArquivoTexto;
	}
	

}
