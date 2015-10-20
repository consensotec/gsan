package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.action.ActionForm;

public class FiltrarMotivoOcorrenciaOperacionalActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String descricaoAbreviada;
	private Integer tipoOcorrencia;
	private short indicadorUso;
	private String indicadorAtualizar;
	
	public FiltrarMotivoOcorrenciaOperacionalActionForm(){}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public Integer getTipoOcorrencia() {
		return tipoOcorrencia;
	}
	public void setTipoOcorrencia(Integer tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}
	public short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
}
