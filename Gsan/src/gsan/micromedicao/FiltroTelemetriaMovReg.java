package gsan.micromedicao;

import gsan.util.filtro.Filtro;

public class FiltroTelemetriaMovReg extends Filtro {
private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public FiltroTelemetriaMovReg() {

	}

	public FiltroTelemetriaMovReg(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
