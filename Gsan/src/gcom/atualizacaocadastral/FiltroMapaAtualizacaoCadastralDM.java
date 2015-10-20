package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroMapaAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = -1478030519268985507L;

	public FiltroMapaAtualizacaoCadastralDM() {
	}
	
	public FiltroMapaAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";

	public final static String ID_LOCALIDADE = "localidade.id";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String ID_SETOR_COMERCIAL = "setorComercial.id";
	
	public final static String ID_QUADRA = "quadra.id";
	
	public final static String SETOR_COMERCIAL = "setorComercial";
	
	public final static String COD_SETOR_COMERCIAL = "setorComercial.codigo";

	public final static String QUADRA = "quadra";
	
	public final static String NUMERO_QUADRA = "quadra.numeroQuadra";

}
