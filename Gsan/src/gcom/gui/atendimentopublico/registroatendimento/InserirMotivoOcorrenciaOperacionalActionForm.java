package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.action.ActionForm;

public class InserirMotivoOcorrenciaOperacionalActionForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private String descricaoAbreviada;
	private Integer tipoOcorrencia;
	
	public InserirMotivoOcorrenciaOperacionalActionForm(){}
	
	public String getDescricao(){
		return this.descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public String getDescricaoAbreviada(){
		return this.descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada){
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public Integer getTipoOcorrencia(){
		return this.tipoOcorrencia;
	}
	public void setTipoOcorrencia(Integer tipoOcorrencia){
		this.tipoOcorrencia = tipoOcorrencia;
	}
}
