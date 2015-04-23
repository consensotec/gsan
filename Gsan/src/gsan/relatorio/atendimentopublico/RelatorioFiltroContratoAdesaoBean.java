package gsan.relatorio.atendimentopublico;

import gsan.relatorio.RelatorioBean;

public class RelatorioFiltroContratoAdesaoBean implements RelatorioBean {
	
	private String id;
	private String descricao;
	
	
	public RelatorioFiltroContratoAdesaoBean(String id,
			String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}