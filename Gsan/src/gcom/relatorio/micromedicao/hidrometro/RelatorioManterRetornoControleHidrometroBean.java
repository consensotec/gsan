package gcom.relatorio.micromedicao.hidrometro;

import gcom.relatorio.RelatorioBean;



public class RelatorioManterRetornoControleHidrometroBean implements RelatorioBean {
	private String codigo;

	private String descricao;
	
	private String indicadorDeGeracao;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterMotivoCorteBean
	 * 
	 * @param codigo
	 *            Descri��o do par�metro
	 * @param gerenciaRegional
	 *            Descri��o do par�metro
	 * @param nome
	 *            Descri��o do par�metro
	 * @param indicadorUso
	 *            Descri��o do par�metro
	 */

	public RelatorioManterRetornoControleHidrometroBean(String codigo, String descricao,
			String indicadorDeGeracao, String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.indicadorDeGeracao = indicadorDeGeracao;		
		this.indicadorUso = indicadorUso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

	public String getIndicadorDeGeracao() {
		return indicadorDeGeracao;
	}

	public void setIndicadorDeGeracao(String indicadorDeGeracao) {
		this.indicadorDeGeracao = indicadorDeGeracao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}



}