package gsan.gui.cadastro.imovel;

import gsan.relatorio.RelatorioBean;

/**
 * [UC1373] Gerar Relatório Histórico Imóveis Programa Especial.
 * 
 * @author Jonathan Marcos
 * @date 06/05/2013
 *  
 */

public class RelatorioHistoriocoImoveisProgramaEspecialBean implements RelatorioBean {
	
	//atributos	
	private String matricula;
	private String inscricao;
	private String nome;
	private String dataApresentacao;
	private String dataInicio;
	private String dataInclusao;
	private String usuarioInclusao;
	private String dataSaida;
	private String dataSuspensao;
	private String UsuarioSuspensao;
	private String formaSuspensao;
	private String numero;
	
	//controlador
	public RelatorioHistoriocoImoveisProgramaEspecialBean(RelatorioHistoriocoImoveisProgramaEspecialHelper helper){
		
		this.matricula = helper.getMatricula();
		this.inscricao = helper.getInscricao();
		this.nome = helper.getNome();
		this.dataApresentacao = helper.getDataApresentacao();
		this.dataInicio = helper.getDataInicio();
		this.dataInclusao = helper.getDataInclusao();
		this.usuarioInclusao = helper.getUsuarioInclusao();
		this.dataSaida = helper.getDataSaida();
		this.dataSuspensao = helper.getDataSuspensao();
		this.UsuarioSuspensao = helper.getUsuarioSuspensao();
		this.formaSuspensao = helper.getFormaSuspensao();
		this.numero = helper.getNumero();
		
	}
	
	//métodos get e set
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(String dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public String getUsuarioInclusao() {
		return usuarioInclusao;
	}
	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getDataSuspensao() {
		return dataSuspensao;
	}
	public void setDataSuspensao(String dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}
	public String getUsuarioSuspensao() {
		return UsuarioSuspensao;
	}
	public void setUsuarioSuspensao(String usuarioSuspensao) {
		UsuarioSuspensao = usuarioSuspensao;
	}
	public String getFormaSuspensao() {
		return formaSuspensao;
	}
	public void setFormaSuspensao(String formaSuspensao) {
		this.formaSuspensao = formaSuspensao;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}	
}
