package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1239] Gerar Relatório de Penalidades por Índice de Atuação e Sucesso Financeiro
 * 
 * @author Hugo Azevedo
 * @date 26/10/2011
 */
public class GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm extends ActionForm{
    

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
