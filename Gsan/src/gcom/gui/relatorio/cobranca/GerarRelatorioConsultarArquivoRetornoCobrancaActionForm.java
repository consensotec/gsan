package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioConsultarArquivoRetornoCobrancaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String dataVencimentoInicial;
	private String dataVencimentoFinal;

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
}