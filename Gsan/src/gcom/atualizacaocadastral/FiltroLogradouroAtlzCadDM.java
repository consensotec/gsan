package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroLogradouroAtlzCadDM extends Filtro implements Serializable {

private static final long serialVersionUID = 1L;
	
    public static final String ID = "id";

    public static final String NOME = "nome";
    
    public static final String NOME_POPULAR = "nomePopular";
    
    public static final String INDICADOR_ATULIZADO = "indicadorAtualizado";
    
    public static final String MUNICIPIO = "municipio";

    public static final String ID_MUNICIPIO = "municipio.id";
    
    public static final String NOME_MUNICIPIO = "municipio.nome";
    
    public static final String UF = "municipio.unidadeFederacao";
    
    public static final String LOGRADOUROTIPO = "logradouroTipo"; 
    
    public static final String ID_LOGRADOUROTIPO = "logradouroTipo.id";
    
    public static final String LOGRADOUROTITULO = "logradouroTitulo";

    public static final String ID_LOGRADOUROTITULO = "logradouroTitulo.id";
    
    public static final String DESCRICAOABREVIADA_LOGRADOUROTIPO = "logradouroTipo.descricaoAbreviada";
    
    public static final String DESCRICAOABREVIADA_LOGRADOUROTITULO = "logradouroTitulo.descricaoAbreviada";
    
    public static final String LOCALIDADE = "localidade";
    
    public static final String ID_LOCALIDADE = "localidade.id";
    
    public static final String EMPRESA = "empresa";
    
    public static final String ID_EMPRESA = "empresa.id";
    
    public static final String CODIGO = "codigo";
    
    public FiltroLogradouroAtlzCadDM() {
    }

    public FiltroLogradouroAtlzCadDM(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
