package gsan.gui.relatorio.atendimentopublico;

import java.io.Serializable;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class FiltrarRelatorioTiposServicoHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tipoMedicao;
	private String dataInicial;
	private String dataFinal;
		
	public FiltrarRelatorioTiposServicoHelper(){}
	
	public FiltrarRelatorioTiposServicoHelper(String tipoMedicao, String dataInicial, String dataFinal){
		this.tipoMedicao = tipoMedicao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
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