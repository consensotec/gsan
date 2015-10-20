package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC ] - 
 * 
 * @author Rodrigo Cabral
 * @date 17/08/2012
 *
 */

public class ConsultarHistoricoProgramaEspecialActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String inscricaoImovel;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	
}
