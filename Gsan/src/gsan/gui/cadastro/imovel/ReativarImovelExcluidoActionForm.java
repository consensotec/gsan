package gsan.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;
/**
 * Descrição da Classe
 * 
 * @author Diogo Luiz
 * @date 13/12/2013 
 * 
 */
public class ReativarImovelExcluidoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String matricula;
	
	private String descricaoImovel;
	
	private String nomeCliente;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String situacaoAguaAnterior;
	
	private String situacaoEsgotoAnterior;
	
	private String perfilImovel;
	
	private String perfilImovelAnterior;
	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getSituacaoAguaAnterior() {
		return situacaoAguaAnterior;
	}

	public void setSituacaoAguaAnterior(String situacaoAguaAnterior) {
		this.situacaoAguaAnterior = situacaoAguaAnterior;
	}

	public String getSituacaoEsgotoAnterior() {
		return situacaoEsgotoAnterior;
	}

	public void setSituacaoEsgotoAnterior(String situacaoEsgotoAnterior) {
		this.situacaoEsgotoAnterior = situacaoEsgotoAnterior;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getPerfilImovelAnterior() {
		return perfilImovelAnterior;
	}

	public void setPerfilImovelAnterior(String perfilImovelAnterior) {
		this.perfilImovelAnterior = perfilImovelAnterior;
	}
}