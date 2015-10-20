package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Rossiter
 * @date 27/09/2010
 */
public class FiltroTelemetriaRetMot extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String INDICADOR_MOV_ACEITO = "indicadorMovAceito";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String ID = "id";
	public final static String DESCRICAO = "descricaoRetorno";
	
	public FiltroTelemetriaRetMot() {

	}

	public FiltroTelemetriaRetMot(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
