package gsan.relatorio.atendimentopublico.ordemservico;

import gsan.relatorio.RelatorioBean;

public class RelatorioQuantImovOSSeletivaBean implements RelatorioBean{
	
	private String localidade;
	private String setor;
	private String quadra;
	private String anormalidade;
	private Integer qtdImoveis;
	
	
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetorComercial(String setor) {
		this.setor = setor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getAnormalidade() {
		return anormalidade;
	}
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}
	public Integer getQtdImoveis() {
		return qtdImoveis;
	}
	public void setQtdImoveis(Integer qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
	
	

}
