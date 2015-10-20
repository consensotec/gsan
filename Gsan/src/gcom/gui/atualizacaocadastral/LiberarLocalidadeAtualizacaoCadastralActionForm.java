package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1327] - Liberar Localidade para Atualização Cadastral
 * 
 * @author André Miranda
 * @date 26/08/2014
 */
public class LiberarLocalidadeAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String localidade;

	private String nomeLocalidade;

	private String setorComercial;

	private String nomeSetorComercial;

	private String idEmpresa;

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
}
