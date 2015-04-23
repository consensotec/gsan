package gsan.relatorio.cadastro;

import gsan.relatorio.RelatorioBean;

public class RelatorioQuantitativoMensagensPendentesBean implements RelatorioBean {
	
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private String quantidadeImoveisLocalidade;
	
	private String descricaoMensagem;
	private Integer quantidadeImoveisPendentes;
	private String quantidadeImoveisComInconsistencias;

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
	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}
	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}
	public Integer getQuantidadeImoveisPendentes() {
		return quantidadeImoveisPendentes;
	}
	public void setQuantidadeImoveisPendentes(Integer quantidadeImoveisPendentes) {
		this.quantidadeImoveisPendentes = quantidadeImoveisPendentes;
	}
	public String getQuantidadeImoveisLocalidade() {
		return quantidadeImoveisLocalidade;
	}
	public void setQuantidadeImoveisLocalidade(String quantidadeImoveisLocalidade) {
		this.quantidadeImoveisLocalidade = quantidadeImoveisLocalidade;
	}	
	public String getQuantidadeImoveisComInconsistencias() {
		return quantidadeImoveisComInconsistencias;
	}
	public void setQuantidadeImoveisComInconsistencias(
			String quantidadeImoveisComInconsistencias) {
		this.quantidadeImoveisComInconsistencias = quantidadeImoveisComInconsistencias;
	}
	
}
