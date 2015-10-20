package gcom.gui.portal.caern;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rafael Pinto
 * @date 09/08/2013
 */
public class ExibirLojasAtendimentoPresencialPortalCaernActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private Integer municipio;

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
}