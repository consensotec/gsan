package gsan.atualizacaocadastral;

import gsan.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroLogradouroBairroAtlzCadDM extends Filtro implements Serializable {

private static final long serialVersionUID = 1L;
	
    public final static String ID = "id";

    public final static String ID_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCadDM.id";
    
    public final static String NOME_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCadDM.nome";

    public final static String NOME_POPULAR_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCadDM.nomePopular";
    
    public final static String BAIRRO = "bairro";
    
    public final static String ID_BAIRRO = "bairro.id";
    
    public final static String CD_BAIRRO = "bairro.codigo";

    public final static String NOME_BAIRRO = "bairro.nome";

    public final static String ID_MUNICIPIO = "bairro.municipio.id";
    
    public final static String CODIGO_LOGRADOURO_ATLZ_CAD = "logradouroAtlzCadDM.codigo";
    
    public FiltroLogradouroBairroAtlzCadDM() {
    }

    public FiltroLogradouroBairroAtlzCadDM(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
