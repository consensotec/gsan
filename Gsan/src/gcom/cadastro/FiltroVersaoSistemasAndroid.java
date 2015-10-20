package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroVersaoSistemasAndroid extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor Default
	 */
	public FiltroVersaoSistemasAndroid() {
	}
	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroVersaoSistemasAndroid(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";

	public final static String VERSAO_NUMERO = "numeroVersao";

	public final static String SISTEMA_ANDROID = "sistemaAndroid";
	
	public final static String SISTEMA_ANDROID_ID = "sistemaAndroid.id";

}
