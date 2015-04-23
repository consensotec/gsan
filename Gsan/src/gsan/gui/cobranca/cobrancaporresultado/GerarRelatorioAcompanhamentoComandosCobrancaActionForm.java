package gsan.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
 * 
 * @author Mariana Victor
 * @date 08/11/2011
 */
public class GerarRelatorioAcompanhamentoComandosCobrancaActionForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String idEmpresaContratada;
	private String dataPeriodoExInicial;
	private String dataPeriodoExFinal;
	
	public String getIdEmpresaContratada() {
		return idEmpresaContratada;
	}
	public void setIdEmpresaContratada(String idEmpresaContratada) {
		this.idEmpresaContratada = idEmpresaContratada;
	}

	public String getDataPeriodoExInicial() {
		return dataPeriodoExInicial;
	}

	public void setDataPeriodoExInicial(String dataPeriodoExInicial) {
		this.dataPeriodoExInicial = dataPeriodoExInicial;
	}

	public String getDataPeriodoExFinal() {
		return dataPeriodoExFinal;
	}

	public void setDataPeriodoExFinal(String dataPeriodoExFinal) {
		this.dataPeriodoExFinal = dataPeriodoExFinal;
	}

}
