package gsan.atualizacaocadastral;

import java.io.Serializable;

import gsan.util.filtro.Filtro;

/**
 * 
 * @author Diogo Luiz
 * @date 27/08/2014
 *
 */
public class FiltroClienteFoneAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteFoneAtualizacaoCadastralDM(){
	}
	
	public FiltroClienteFoneAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastralDM.id";
	
	public final static String CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastralDM";

}
