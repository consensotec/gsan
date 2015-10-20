package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.action.ActionForm;

/**
 * action responsável pela exibição do Relatório de Pagamento com Baixa Automática
 *
 * @author Diogo Luiz
 * @created 09/07/2013
 */

public class GerarRelatorioPagamentoBaixaAutomaticaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String tipo;
	
	private String idMatricula;
	
	private String nomeMatricula;
	
	private String dataInicial;
	
	private String dataFinal;

	private String indicadorEstado;

	private String indicadorGerenciaRegional;	               
		
	private String indicadorUnidadeNegocio;
	
	private String indicadorLocalidade;
	
	private String indicadorSetorComercial;	
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idLocalidade;
	
	private String idSetorComercial;
	
	private String opcaoTotalizacao;
	
	private String baixaAutomaticaPagamento;
	
	private String faixaDiferencaValoresInicial;
	
	private String faixaDiferencaValoresFinal;
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getidMatricula() {
		return idMatricula;
	}

	public void setidMatricula(String idMatricula) {
		this.idMatricula = idMatricula;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	public String getIndicadorGerenciaRegional() {
		return indicadorGerenciaRegional;
	}

	public void setIndicadorGerenciaRegional(String indicadorGerenciaRegional) {
		this.indicadorGerenciaRegional = indicadorGerenciaRegional;
	}

	public String getIndicadorUnidadeNegocio() {
		return indicadorUnidadeNegocio;
	}

	public void setIndicadorUnidadeNegocio(String indicadorUnidadeNegocio) {
		this.indicadorUnidadeNegocio = indicadorUnidadeNegocio;
	}

	public String getIndicadorLocalidade() {
		return indicadorLocalidade;
	}

	public void setIndicadorLocalidade(String indicadorLocalidade) {
		this.indicadorLocalidade = indicadorLocalidade;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	
	public String getIndicadorSetorComercial() {
		return indicadorSetorComercial;
	}

	public void setIndicadorSetorComercial(String indicadorSetorComercial) {
		this.indicadorSetorComercial = indicadorSetorComercial;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}	

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getBaixaAutomaticaPagamento() {
		return baixaAutomaticaPagamento;
	}

	public void setBaixaAutomaticaPagamento(String baixaAutomaticaPagamento) {
		this.baixaAutomaticaPagamento = baixaAutomaticaPagamento;
	}

	public String getFaixaDiferencaValoresInicial() {
		return faixaDiferencaValoresInicial;
	}

	public void setFaixaDiferencaValoresInicial(String faixaDiferencaValoresInicial) {
		this.faixaDiferencaValoresInicial = faixaDiferencaValoresInicial;
	}

	public String getFaixaDiferencaValoresFinal() {
		return faixaDiferencaValoresFinal;
	}

	public void setFaixaDiferencaValoresFinal(String faixaDiferencaValoresFinal) {
		this.faixaDiferencaValoresFinal = faixaDiferencaValoresFinal;
	}

	public String getNomeMatricula() {
		return nomeMatricula;
	}

	public void setNomeMatricula(String nomeMatricula) {
		this.nomeMatricula = nomeMatricula;
	}
}
