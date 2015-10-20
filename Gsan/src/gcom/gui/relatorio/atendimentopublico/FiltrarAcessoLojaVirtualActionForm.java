package gcom.gui.relatorio.atendimentopublico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
 * 
 * @author Flávio Ferreira
 * @since 27/09/2013
 */

public class FiltrarAcessoLojaVirtualActionForm extends ActionForm{

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String tipoAtendimento;
	private String indicadorServicoExecutado;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}
	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}
	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}
	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}
	
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	public String getIndicadorServicoExecutado() {
		return indicadorServicoExecutado;
	}
	public void setIndicadorServicoExecutado(String indicadorServicoExecutado) {
		this.indicadorServicoExecutado = indicadorServicoExecutado;
	}
	

}
