package gcom.cadastro.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC 1165] - Confirmar Alterações Cadastrais
 * 
 * @author Davi Menezes
 * @date 09/06/2012
 *
 */
public class FiltroClienteFoneAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteFoneAtualizacaoCadastral(){
	
	}
	
	public FiltroClienteFoneAtualizacaoCadastral(String orderBy){
		this.campoOrderBy = orderBy;
	}
	
	public final static String ID = "id";
	
	public final static String CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastral";
	
	public final static String ID_CLIENTE_ATUALIZACAO_CADASTRAL = "clienteAtualizacaoCadastral.id";
	
}
