package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCertidaoNegativaDebito extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCertidaoNegativaDebito object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCertidaoNegativaDebito(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroCertidaoNegativaDebito
     */
    public FiltroCertidaoNegativaDebito() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    public final static String DATA_GERACAO = "dataGeracao";

    public final static String DATA_VENCIMENTO = "dataVencimento";
    
    public final static String NUMERO_AUTENTICACAO = "numeroAutenticacao";
    
    public final static String DOCUMENTO_GERADO = "documentoGerado";
    
    public final static String CLIENTE = "cliente";
    
    public final static String CLIENTE_ID = "cliente.id";
    
    public final static String ORDEM_SERVICO_IMOVEL_ID = "ordemServico.imovel.id";
    
    public final static String IMOVEL = "imovel";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String USUARIO = "usuario";
    
    public final static String USUARIO_ID = "usuario.id";

}