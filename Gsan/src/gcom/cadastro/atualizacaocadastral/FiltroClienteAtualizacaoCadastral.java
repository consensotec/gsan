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
public class FiltroClienteAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteAtualizacaoCadastral(){
		
	}
	
	public FiltroClienteAtualizacaoCadastral(String orderBy){
		this.campoOrderBy = orderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_CLIENTE = "idCliente";
	
	public final static String IMOVEL_ATUALIZACAO_CADADASTRAL = "imovelAtualizacaoCadastral";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral.id";

}
