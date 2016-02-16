package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm extends ActionForm {


	private static final long serialVersionUID = 1L;
	
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	private String idCliente;
	private String tipoDeRelatorio;
	
	
	public String getTipoDeRelatorio() {
		return tipoDeRelatorio;
	}
	public void setTipoDeRelatorio(String tipoDeRelatorio) {
		this.tipoDeRelatorio = tipoDeRelatorio;
	}
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
}