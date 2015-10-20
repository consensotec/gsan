package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class RelatorioDebitosCobradosImovelBean implements RelatorioBean{

	private String anoMesRefDebito;
	private String debitoTipo;
	private String valorCalculado;
	private String valorCobrado;
	private String mesAnoCobranca;
	private String numeroPrestacao;
	
		
	//Construtor inicializando todas as variáveis de instância.
	public RelatorioDebitosCobradosImovelBean(String anoMesRefDebito, String debitoTipo, String valorCalculado, 
			String valorCobrado, String mesAnoCobranca, String numeroPrestacao) {
		
		this.anoMesRefDebito = anoMesRefDebito;
		this.debitoTipo = debitoTipo;
		this.valorCalculado = valorCalculado;
		this.valorCobrado = valorCobrado;
		this.numeroPrestacao = numeroPrestacao;
		this.mesAnoCobranca = mesAnoCobranca;
	}
	
	//Construtor vazio
	public RelatorioDebitosCobradosImovelBean() {
	}

	public String getValorCalculado() {
		return valorCalculado;
	}

	public void setValorCalculado(String valorCalculado) {
		this.valorCalculado = valorCalculado;
	}

	public String getAnoMesRefDebito() {
		return anoMesRefDebito;
	}

	public void setAnoMesRefDebito(String anoMesRefDebito) {
		this.anoMesRefDebito = anoMesRefDebito;
	}

	public String getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(String debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public String getValorCobrado() {
		if (valorCobrado!=null && !valorCobrado.equals("")){
			return valorCobrado;
		}
		return " ";
	}

	public void setValorCobrado(String valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public String getNumeroPrestacao() {
		return numeroPrestacao;
	}

	public void setNumeroPrestacao(String numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public String getMesAnoCobranca() {
		if (mesAnoCobranca!=null && !mesAnoCobranca.equals("")){
			return mesAnoCobranca;
		}
		return " ";
	}

	public void setMesAnoCobranca(String mesAnoCobranca) {
		this.mesAnoCobranca = mesAnoCobranca;
	}

	

	
}