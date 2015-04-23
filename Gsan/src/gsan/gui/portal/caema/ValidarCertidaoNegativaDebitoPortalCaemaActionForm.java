package gsan.gui.portal.caema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * [UC1300] Verificar Autenticidade da Certidão Negativa de Débito
 * 
 * @author Mariana Victor
 * @date 15/03/2012
 */
public class ValidarCertidaoNegativaDebitoPortalCaemaActionForm 
	extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String numeroAutenticacao;
	
	private String matriculaImovel;

	public String getNumeroAutenticacao() {
		return numeroAutenticacao;
	}

	public void setNumeroAutenticacao(String numeroAutenticacao) {
		this.numeroAutenticacao = numeroAutenticacao;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

}