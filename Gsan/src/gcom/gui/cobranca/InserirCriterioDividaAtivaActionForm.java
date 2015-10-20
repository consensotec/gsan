package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1587] - Gerar Dívida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class InserirCriterioDividaAtivaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String vencimentoConta;
	
	private String[] esferaPoder;
	
	private String[] tipoCliente;
	
	private String valorInicial;
	
	private String valorFinal;

	public String getVencimentoConta() {
		return vencimentoConta;
	}

	public void setVencimentoConta(String vencimentoConta) {
		this.vencimentoConta = vencimentoConta;
	}

	public String[] getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String[] getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String[] tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}

	public String getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	public void reset(){
		this.vencimentoConta = "";
		this.valorInicial = "";
		this.valorFinal = "";
		this.esferaPoder = null;
		this.tipoCliente = null;
	}
	
}
