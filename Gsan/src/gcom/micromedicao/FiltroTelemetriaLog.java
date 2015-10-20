package gcom.micromedicao;

import gcom.util.filtro.Filtro;

public class FiltroTelemetriaLog extends Filtro {
private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public FiltroTelemetriaLog() {

	}

	public FiltroTelemetriaLog(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
