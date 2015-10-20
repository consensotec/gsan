package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Di�rios da Arrecada��o
 * 
 * Gerar Relat�rio Dados Di�rios da Arrecada��o - Parametros
 * 
 * @author Mariana Victor
 * @date 01/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoParametrosSubBean implements RelatorioBean {
	
	private String descricao;
	
	
	public RelatorioDadosDiariosArrecadacaoParametrosSubBean() {
		super();
	}

	public RelatorioDadosDiariosArrecadacaoParametrosSubBean(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
