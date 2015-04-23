package gsan.atualizacaocadastral;

import java.io.Serializable;

import gsan.util.filtro.Filtro;

/**
 * 
 * @author Diogo Luiz
 * @date 27/08/2014
 *
 */
public class FiltroImovelSubcategoriaAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroImovelSubcategoriaAtualizacaoCadastralDM(){
		
	}
	
	public FiltroImovelSubcategoriaAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
	public final static String ID_IMOVEL = "idImovel";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM";
}
