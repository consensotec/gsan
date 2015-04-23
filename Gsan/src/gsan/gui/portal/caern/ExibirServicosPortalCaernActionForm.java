package gsan.gui.portal.caern;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Classe Responsável por armazenar o valor da matrícula e do usuário para
 * mostrar os Serviços da Loja Virtual CAERN
 * 
 * @author Rafael Pinto
 * 
 * @date 15/07/2013
 */
public class ExibirServicosPortalCaernActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String matricula;

	private String nomeUsuario;

	private String cpfCnpjSolicitante;

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		this.cpfCnpjSolicitante = null;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfCnpjSolicitante() {
		return cpfCnpjSolicitante;
	}

	public void setCpfCnpjSolicitante(String cpfCnpjSolicitante) {
		this.cpfCnpjSolicitante = cpfCnpjSolicitante;
	}
}