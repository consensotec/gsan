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
public class FiltroImovelSubcategoriaAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroImovelSubcategoriaAtualizacaoCadastral(){
		
	}
	
	public FiltroImovelSubcategoriaAtualizacaoCadastral(String orderBy) {
		this.campoOrderBy = orderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastral";

}
