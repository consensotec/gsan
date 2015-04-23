package gsan.seguranca.parametrosistema;

import gsan.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroParametroSistema extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FiltroParametroSistema() {
		
	}
	
	public FiltroParametroSistema(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;		
	}
	
	 /**
     * Description of the Field
     */
	public final static String ID = "id";
	
	
    /**
     * Description of the Field
     */
	public final static String CODIGO_CONSTANTE = "codigoConstante";
	
	/**
     * Description of the Field
     */
	public final static String NOME = "nome";
	
    /**
     * Description of the Field
     */
	public final static String TIPO_PARAMETRO = "parametroTipo";
		
    /**
     * Description of the Field
     */
	public final static String INDICADOR_PARAMETRO_RESTRITO = "indicadorParametroRestrito";
	
    /**
     * Description of the Field
     */
	public final static String INDICADOR_USO = "indicadorUso";
	
    /**
     * Description of the Field
     */
	public final static String MODULO_ID = "modulo.id";
	
	
	/**
     * Description of the Field
     */
	public final static String MODULO= "modulo";
	
	
}
