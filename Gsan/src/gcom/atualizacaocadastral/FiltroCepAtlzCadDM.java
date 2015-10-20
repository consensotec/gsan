
package gcom.atualizacaocadastral;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroCepAtlzCadDM extends Filtro implements Serializable{

private static final long serialVersionUID = 1L;
	
    public static final String ID_CEP_ATLZ_CAD = "id";

    public static final String CODIGO_CEP_ATLZ_CAD = "codigoCep";

    public FiltroCepAtlzCadDM() {
    }

    public FiltroCepAtlzCadDM(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
