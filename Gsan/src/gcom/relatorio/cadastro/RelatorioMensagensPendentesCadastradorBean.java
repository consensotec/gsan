package gcom.relatorio.cadastro;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioMensagensPendentesCadastradorBean implements RelatorioBean {

	private Integer idLocalidade;
	private String descricaoLocalidade;
	
	private Integer idCadastrador;
	private String nomeCadastrador;
	
	private String descricaoMensagem;
	private BigDecimal quantidadeImoveis;
	private BigDecimal percentagem;
	
	private Integer quantidadeTotal;
	
	private Integer quantidadeTotalPorCadastrador;
	private BigDecimal percentagemMsgPorCadastrador;
//	private Integer quantidadeTotalGeral;
//	private BigDecimal percentagemTotalGeralPorTotalLocalidade;
	
	private Integer quantidadeTotalGeralCadastrador;
	private BigDecimal percTotalGeralCadastradorPorTotalLocalidade;
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public Integer getIdCadastrador() {
		return idCadastrador;
	}
	public void setIdCadastrador(Integer idCadastrador) {
		this.idCadastrador = idCadastrador;
	}
	public String getNomeCadastrador() {
		return nomeCadastrador;
	}
	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}
	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}
	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}
	public BigDecimal getQuantidadeImoveis() {
		return quantidadeImoveis;
	}
	public void setQuantidadeImoveis(BigDecimal quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}
	public BigDecimal getPercentagem() {
		return percentagem;
	}
	public void setPercentagem(BigDecimal percentagem) {
		this.percentagem = percentagem;
	}
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public BigDecimal getPercentagemMsgPorCadastrador() {
		return percentagemMsgPorCadastrador;
	}
	public void setPercentagemMsgPorCadastrador(
			BigDecimal percentagemMsgPorCadastrador) {
		this.percentagemMsgPorCadastrador = percentagemMsgPorCadastrador;
	}
	public Integer getQuantidadeTotalPorCadastrador() {
		return quantidadeTotalPorCadastrador;
	}
	public void setQuantidadeTotalPorCadastrador(
			Integer quantidadeTotalPorCadastrador) {
		this.quantidadeTotalPorCadastrador = quantidadeTotalPorCadastrador;
	}
	public Integer getQuantidadeTotalGeralCadastrador() {
		return quantidadeTotalGeralCadastrador;
	}
	public void setQuantidadeTotalGeralCadastrador(
			Integer quantidadeTotalGeralCadastrador) {
		this.quantidadeTotalGeralCadastrador = quantidadeTotalGeralCadastrador;
	}
	public BigDecimal getPercTotalGeralCadastradorPorTotalLocalidade() {
		return percTotalGeralCadastradorPorTotalLocalidade;
	}
	public void setPercTotalGeralCadastradorPorTotalLocalidade(
			BigDecimal percTotalGeralCadastradorPorTotalLocalidade) {
		this.percTotalGeralCadastradorPorTotalLocalidade = percTotalGeralCadastradorPorTotalLocalidade;
	}
	
	
}