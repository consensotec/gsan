package gcom.seguranca.parametrosistema;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroParametroTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FiltroParametroTipo() {
		
	}
	
	public FiltroParametroTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;		
	}
	
	 /**
     * Description of the Field
     */
	public final static String ID = "id";
	
    /**
     * Description of the Field
     */
	public final static String INDICADOR_USO = "indicadorUso";
	
}
