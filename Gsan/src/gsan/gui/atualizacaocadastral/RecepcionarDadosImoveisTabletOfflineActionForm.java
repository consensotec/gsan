package gsan.gui.atualizacaocadastral;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class RecepcionarDadosImoveisTabletOfflineActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private FormFile formFile;

	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
}