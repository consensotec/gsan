package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
 * 
 * @author Diogo Luiz
 * @Date 20/08/2013
 *
 */
public class RelatorioClienteCpfCnpjValidadosMobileBean implements RelatorioBean {
	
	private Integer codCliente;
	private String clieGsan;
	private String clieMobile;
	private String valorAnterior;
	private String valorAtual;
	
	
	public Integer getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getClieGsan() {
		return clieGsan;
	}
	
	public void setClieGsan(String clieGsan) {
		this.clieGsan = clieGsan;
	}
	
	public String getClieMobile() {
		return clieMobile;
	}
	
	public void setClieMobile(String clieMobile) {
		this.clieMobile = clieMobile;
	}
	
	public String getValorAnterior() {
		return valorAnterior;
	}
	
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	
	public String getValorAtual() {
		return valorAtual;
	}
	
	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}
	
	
}
