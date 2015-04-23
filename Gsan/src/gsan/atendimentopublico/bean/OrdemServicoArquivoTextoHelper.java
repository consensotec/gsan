package gsan.atendimentopublico.bean;

import java.io.Serializable;

public class OrdemServicoArquivoTextoHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private String idOrdemServico;
	
	private String matricula;
	
	private String codigoSituacao;
	
	private String situacao;
	
	private String dataHoraRecebimento;
	
	private String ordemServicoConferida;
	
	private String inscricao;
	
	private String enderecoAbreviado;
	
	private String categoriaPrincipal;
	
	private String quantidadeEconomias;
	
	private String grupoFaturamento;
	
	private String situacaoLigacaoAgua;
	
	private String consumoMedio;
	
	private String situacaoLigacaoEsgoto;
	
	private String consumoFixoEsgoto;
	
	private String ultimaAlteracao;
	
	public String getDataHoraRecebimento() {
		return dataHoraRecebimento;
	}

	public void setDataHoraRecebimento(String dataHoraRecebimento) {
		this.dataHoraRecebimento = dataHoraRecebimento;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getOrdemServicoConferida() {
		return ordemServicoConferida;
	}

	public void setOrdemServicoConferida(String ordemServicoConferida) {
		this.ordemServicoConferida = ordemServicoConferida;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getEnderecoAbreviado() {
		return enderecoAbreviado;
	}

	public void setEnderecoAbreviado(String enderecoAbreviado) {
		this.enderecoAbreviado = enderecoAbreviado;
	}

	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getConsumoFixoEsgoto() {
		return consumoFixoEsgoto;
	}

	public void setConsumoFixoEsgoto(String consumoFixoEsgoto) {
		this.consumoFixoEsgoto = consumoFixoEsgoto;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getHint() {
		String hint = "<strong>Inscrição:</strong> " + 
		        this.inscricao + "<br>" +
		        "<strong>Endereço:</strong> " +
		        this.enderecoAbreviado + "<br>" +
		        "<strong>Categoria Principal:</strong> " +
		        this.categoriaPrincipal + "<br>" +                     
		        "<strong>Quantidade de Economias:</strong> " +
		        this.quantidadeEconomias + "<br>" +                     
		        "<strong>Grupo de Faturamento:</strong> " +
		        this.grupoFaturamento + "<br>" +
		        "<strong>Situação da Ligação de Água:</strong> " +
		        this.situacaoLigacaoAgua + "<br>";
		
		if (this.consumoMedio != null 
				&& !this.consumoMedio.trim().equals("")) {
			hint = hint +                     
			        "<strong>Consumo Médio:</strong> " +
			        this.consumoMedio + "<br>";
		}
		
		hint = hint + 
				"<strong>Situação da Ligação de Esgoto:</strong> " +
		        this.situacaoLigacaoEsgoto + "<br>";   
		
		if (this.consumoFixoEsgoto != null 
				&& !this.consumoFixoEsgoto.trim().equals("")) {
			hint = hint +                     
					"<strong>Consumo Fixo de Esgoto:</strong> " +
			        this.consumoFixoEsgoto + "<br>";
		}
		
		hint = hint +                     
		        "<strong>Última Alteração:</strong> " +
		        this.ultimaAlteracao + "<br>";
		
		return hint;
	}

}
