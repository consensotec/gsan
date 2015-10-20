package gcom.gui.atendimentopublico.hidrometro;

import org.apache.struts.action.ActionForm;

public class ConsultarEnquadramentoImovelPerfilActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String perfilAlteracaoTipo;	
	
	private String data;
	
	private String perfilAnterior;
	
	private String perfilPosterior;
	
	private String usuario;

	public String getPerfilAlteracaoTipo() {
		return perfilAlteracaoTipo;
	}

	public void setPerfilAlteracaoTipo(String perfilAlteracaoTipo) {
		this.perfilAlteracaoTipo = perfilAlteracaoTipo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPerfilAnterior() {
		return perfilAnterior;
	}

	public void setPerfilAnterior(String perfilAnterior) {
		this.perfilAnterior = perfilAnterior;
	}

	public String getPerfilPosterior() {
		return perfilPosterior;
	}

	public void setPerfilPosterior(String perfilPosterior) {
		this.perfilPosterior = perfilPosterior;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}