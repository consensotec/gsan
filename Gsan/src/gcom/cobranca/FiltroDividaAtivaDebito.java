package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * [UC 1587] - Gerar Divida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class FiltroDividaAtivaDebito extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String DATA_RETIRADA = "dataRetirada";
	
	public final static String ID_CONTA = "conta.id";
	
	public final static String ID_PARCELAMENTO = "parcelamento.id";
	
	public final static String DIVIDA_ATIVA_IMOVEL = "dividaAtivaImovel";

}
