package gsan.gui.portal.caema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Magno Gouveia
 * @date 07/07/2011
 */
public class ExibirLojasAtendimentoPresencialPortalCaemaActionForm extends
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