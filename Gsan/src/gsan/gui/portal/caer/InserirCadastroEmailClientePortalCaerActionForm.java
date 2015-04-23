package gsan.gui.portal.caer;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por email na Loja Virtual da Caer
 * </p>
 * 
 * @author Davi Menezes
 * @date 05/09/2012
 */
public class InserirCadastroEmailClientePortalCaerActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 * [UC1036] Inserir Cadastro de Email do CLiente
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
	 */
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String email;
	private String nomeSolicitante;
	private String cpfSolicitante;
	private String telefoneContato;
	
	private Integer idCliente;

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}
	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getTelefoneContato() {
		return telefoneContato;
	}
	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
}