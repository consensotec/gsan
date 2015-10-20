package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;
import java.io.Serializable;

/**
 * @author Diogo Luiz
 * @date 27/08/2014
 *
 */
public class FiltroClienteAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClienteAtualizacaoCadastralDM(){
		
	}
	
	public FiltroClienteAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
	public final static String ID_CLIENTE = "idCliente";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM";
	
	public final static String DATA_FIM_RELACAO = "dataRelacaoFim";
}

