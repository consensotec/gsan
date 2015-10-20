package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1328] - Suspender Localidade para Atualização Cadastral
 * 
 * @author André Miranda
 * @date 27/08/2014
 */
public class SuspenderLocalidadeAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String [] idsRegistro;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String[] getIdsRegistro() {
		return idsRegistro;
	}

	public void setIdsRegistro(String[] idsRegistro) {
		this.idsRegistro = idsRegistro;
	}
}
