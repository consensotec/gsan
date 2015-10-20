package gcom.atualizacaocadastral;

import java.io.Serializable;
import gcom.util.filtro.Filtro;

public class FiltroLogradouroCepAtlzCadDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String ID = "id";

    public final static String ID_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCad.id";
    
    public final static String CEP_ATLZ_CAD = "cepAtlzCad";

    public final static String ID_CEP_ATLZ_CAD = "cepAtlzCad.id";

    public final static String CODIGO_CEP_ATLZ_CAD = "cepAtlzCad.codigoCep";
    
    public final static String CODIGO_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCad.codigo";
	
    public FiltroLogradouroCepAtlzCadDM() {
    }

    public FiltroLogradouroCepAtlzCadDM(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
