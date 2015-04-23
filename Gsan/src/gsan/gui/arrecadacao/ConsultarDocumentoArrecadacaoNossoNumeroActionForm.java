package gsan.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 1243] - Consultar Documento pelo Nosso número
 * 
 * @author Davi
 * @date 19/10/2011
 *
 */

public class ConsultarDocumentoArrecadacaoNossoNumeroActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
