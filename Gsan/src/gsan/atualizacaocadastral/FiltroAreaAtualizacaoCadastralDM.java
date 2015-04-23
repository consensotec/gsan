package gsan.atualizacaocadastral;

import gsan.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAreaAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAreaAtualizacaoCadastralDM(){
	}
	
	public FiltroAreaAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String CODIGO_SITUACAO = "codigoSituacao";
	
	public final static String EMPRESA = "empresa";
	
	public final static String EMPRESA_ID = "empresa.id";
	
	public final static String EMPRESA_DESCRICAO = "empresa.descricao";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String LOCALIDADE_ID = "localidade.id";
	
	public final static String LOCALIDADE_DESCRICAO = "localidade.descricao";
	
	public final static String SETOR_COMERCIAL= "setorComercial";
	
	public final static String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
	
	public final static String MUNICIPIO = "localidade.municipio";

}
