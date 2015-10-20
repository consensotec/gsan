package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1172] Manter Motivos de N�o Aceita��o de Encerramento de O.S.
 * Classe respons�vel por guardar os valores do formul�rio de
 * motivo_nao_aceitacao_encerramento_os_filtrar.jsp
 * 
 * @author Diogo Peixoto
 * @since 23/05/2011
 * 
 */
public class MotivoNaoAceitacaoEncerramentoOSFiltrarActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String multiplicadorValorServicoDescontarCorteSupressao;
	private String multiplicadorValorServicoDescontarNaoExecutados;
	private String percentualMultaAplicar;
	private String indicadorUso;
	private String[] motivoNaoAceitacaoEncerramentoOSSelectID;
	
	public MotivoNaoAceitacaoEncerramentoOSFiltrarActionForm(){
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMultiplicadorValorServicoDescontarCorteSupressao() {
		return multiplicadorValorServicoDescontarCorteSupressao;
	}

	public void setMultiplicadorValorServicoDescontarCorteSupressao(
			String multiplicadorValorServicoDescontarCorteSupressao) {
		this.multiplicadorValorServicoDescontarCorteSupressao = multiplicadorValorServicoDescontarCorteSupressao;
	}

	public String getMultiplicadorValorServicoDescontarNaoExecutados() {
		return multiplicadorValorServicoDescontarNaoExecutados;
	}

	public void setMultiplicadorValorServicoDescontarNaoExecutados(
			String multiplicadorValorServicoDescontarNaoExecutados) {
		this.multiplicadorValorServicoDescontarNaoExecutados = multiplicadorValorServicoDescontarNaoExecutados;
	}

	public String getPercentualMultaAplicar() {
		return percentualMultaAplicar;
	}

	public void setPercentualMultaAplicar(String percentualMultaAplicar) {
		this.percentualMultaAplicar = percentualMultaAplicar;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String[] getMotivoNaoAceitacaoEncerramentoOSSelectID() {
		return motivoNaoAceitacaoEncerramentoOSSelectID;
	}

	public void setMotivoNaoAceitacaoEncerramentoOSSelectID(
			String[] motivoNaoAceitacaoEncerramentoOSSelectID) {
		this.motivoNaoAceitacaoEncerramentoOSSelectID = motivoNaoAceitacaoEncerramentoOSSelectID;
	}
}