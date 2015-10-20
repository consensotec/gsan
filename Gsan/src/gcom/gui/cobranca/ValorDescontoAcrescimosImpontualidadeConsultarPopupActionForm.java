package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ValorDescontoAcrescimosImpontualidadeConsultarPopupActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String multa;
	
	private String juros;
	
	private String atualizacaoMonetaria;
	
	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getAtualizacaoMonetaria() {
		return atualizacaoMonetaria;
	}

	public void setAtualizacaoMonetaria(String atualizacaoMonetaria) {
		this.atualizacaoMonetaria = atualizacaoMonetaria;
	}

}
