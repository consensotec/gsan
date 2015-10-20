package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC 1359] - Requisições Foto Anormalidade
 * 
 * @author Davi Menezes
 * @date 31/08/2012
 *
 */
public class FiltroMovimentoRoteiroEmpresaFoto extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
	
	public final static String ID_IMOVEL = "imovel.id";
	
	public final static String IMOVEL = "imovel";
	
	public final static String ID_LEITURA_ANORMALIDADE = "leituraAnormalidade";
	
	public final static String LEITURA_ANORMALIDADE = "leituraAnormalidade";

}
