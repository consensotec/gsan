package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroSistemaAndroid extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor Default
	 */
	public FiltroSistemaAndroid() {
	}
	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroSistemaAndroid(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";

	public final static String DESCRICAO_SISTEMA = "descricaoSistema";
	
	public final static String INDICADOR_USO = "indicadorUso";

}
