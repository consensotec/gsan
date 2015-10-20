package gcom.gui.faturamento.consumotarifa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 1441] - Inserir Vigência de Simulação para Tarifa de Consumo
 * 
 * @author Davi Menezes
 * @date 20/12/2013
 *
 */
public class InserirVigenciaSimulacaoTarifaConsumoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String [] registros;

	public String[] getRegistros() {
		return registros;
	}

	public void setRegistros(String[] registros) {
		this.registros = registros;
	}

}
