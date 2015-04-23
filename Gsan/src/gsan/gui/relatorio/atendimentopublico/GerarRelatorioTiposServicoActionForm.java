package gsan.gui.relatorio.atendimentopublico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @since 28/11/2011
 */
public class GerarRelatorioTiposServicoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipoMedicao;
	private String dataInicial;
	private String dataFinal;
	
	public GerarRelatorioTiposServicoActionForm() {
		super();
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	

	
}