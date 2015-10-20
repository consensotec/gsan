package gcom.gui.cadastro.cliente;

import org.apache.struts.action.ActionForm;

/**
 * @author Davi Menezes
 * @date 16/08/2013
 * 
 */
public class AtualizarIndicadorCpfCnpjClientePopupActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idCliente;
	
	private String indicadorValidarCpfCnpj;
	
	private String aba;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIndicadorValidarCpfCnpj() {
		return indicadorValidarCpfCnpj;
	}

	public void setIndicadorValidarCpfCnpj(String indicadorValidarCpfCnpj) {
		this.indicadorValidarCpfCnpj = indicadorValidarCpfCnpj;
	}

	public String getAba() {
		return aba;
	}

	public void setAba(String aba) {
		this.aba = aba;
	}

}
