package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String ano;
	private String idGrupo;
	private String anoGrupo;
	private String opcaoTotalizacao = "1";
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getAnoGrupo() {
		return anoGrupo;
	}
	public void setAnoGrupo(String anoGrupo) {
		this.anoGrupo = anoGrupo;
	}
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	

}
