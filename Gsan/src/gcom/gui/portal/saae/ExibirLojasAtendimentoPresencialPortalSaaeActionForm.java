package gcom.gui.portal.saae;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Cesar Medeiros
 * @date 16/09/2015
 */
public class ExibirLojasAtendimentoPresencialPortalSaaeActionForm extends
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